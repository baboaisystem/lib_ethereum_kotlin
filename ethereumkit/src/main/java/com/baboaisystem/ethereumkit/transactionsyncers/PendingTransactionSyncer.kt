package com.baboaisystem.ethereumkit.transactionsyncers

import com.baboaisystem.ethereumkit.api.jsonrpc.models.RpcTransactionReceipt
import com.baboaisystem.ethereumkit.core.*
import com.baboaisystem.ethereumkit.models.DroppedTransaction
import com.baboaisystem.ethereumkit.models.Transaction
import com.baboaisystem.ethereumkit.models.TransactionReceipt
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.logging.Logger

class PendingTransactionSyncer(
        private val blockchain: IBlockchain,
        private val storage: ITransactionStorage
) : AbstractTransactionSyncer("outgoing_pending_transaction_syncer") {

    private val logger = Logger.getLogger(this.javaClass.simpleName)

    var listener: ITransactionSyncerListener? = null

    override fun onLastBlockNumber(blockNumber: Long) {
        sync()
    }

    private fun sync() {
        logger.info("---> sync() state: $state")

        if (state is EthereumKit.SyncState.Syncing) return

        doSync().subscribeOn(Schedulers.io())
                .subscribe({
                    state = EthereumKit.SyncState.Synced()
                }, {
                    state = EthereumKit.SyncState.NotSynced(it)
                })
                .let { disposables.add(it) }
    }

    private fun doSync(fromTransaction: Transaction? = null): Single<Unit> {
        val pendingTransactions = storage.getPendingTransactions(fromTransaction)
        logger.info("---> doSync() pendingTransactions: ${pendingTransactions.joinToString(separator = ",") { it.hash.toHexString() }}")

        if (pendingTransactions.isEmpty()) {
            return Single.just(Unit)
        }

        val singles: List<Single<Unit>> = pendingTransactions.map { pendingTransaction ->
            blockchain.getTransactionReceipt(pendingTransaction.hash)
                    .flatMap { optionalReceipt ->
                        logger.info("---> doSync() onFetched receipt: ${optionalReceipt.orElse(null)?.transactionHash}")
                        syncTimestamp(pendingTransaction, optionalReceipt.orElse(null))
                    }
        }

        return Single.zip(singles) { }.flatMap {
            doSync(pendingTransactions.last())
        }
    }

    private fun syncTimestamp(transaction: Transaction, receipt: RpcTransactionReceipt?): Single<Unit> {
        if (receipt == null) {
            return Single.just(Unit)
        }

        return blockchain.getBlock(receipt.blockNumber)
                .doOnSuccess { optionalBlock ->
                    logger.info("---> sync() onFetched block: $optionalBlock")

                    handle(transaction, receipt, optionalBlock.orElse(null)?.timestamp)
                }
                .map { }
    }

    private fun handle(transaction: Transaction, receipt: RpcTransactionReceipt, timestamp: Long?) {
        if (timestamp == null) {
            return
        }

        transaction.timestamp = timestamp

        storage.save(transaction)
        storage.save(TransactionReceipt(receipt))
        storage.save(receipt.logs)

        listener?.onTransactionsSynced(storage.getFullTransactions(listOf(receipt.transactionHash)))

        storage.getPendingTransactionList(transaction.nonce).forEach { duplicateTransaction ->
            storage.addDroppedTransaction(DroppedTransaction(hash = duplicateTransaction.hash, replacedWith = transaction.hash))

            listener?.onTransactionsSynced(storage.getFullTransactions(listOf(duplicateTransaction.hash)))
        }
    }

}
