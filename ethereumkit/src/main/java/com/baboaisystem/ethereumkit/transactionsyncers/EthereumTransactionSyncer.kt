package com.baboaisystem.ethereumkit.transactionsyncers

import com.baboaisystem.ethereumkit.api.jsonrpc.models.RpcTransaction
import com.baboaisystem.ethereumkit.core.EthereumKit
import com.baboaisystem.ethereumkit.core.EtherscanTransactionsProvider
import com.baboaisystem.ethereumkit.models.NotSyncedTransaction
import io.reactivex.schedulers.Schedulers
import java.util.logging.Logger

class EthereumTransactionSyncer(
        private val etherscanTransactionsProvider: EtherscanTransactionsProvider
) : AbstractTransactionSyncer("ethereum_transaction_syncer") {

    private val logger = Logger.getLogger(this.javaClass.simpleName)

    override fun onLastBlockNumber(blockNumber: Long) {
        sync()
    }

    private fun sync() {
        logger.info("---> sync() state: $state")

        if (state is EthereumKit.SyncState.Syncing) return

        state = EthereumKit.SyncState.Syncing()

        etherscanTransactionsProvider.getTransactions(lastSyncBlockNumber + 1)
                .map { transactions ->
                    transactions.map { etherscanTransaction ->
                        NotSyncedTransaction(
                                hash = etherscanTransaction.hash,
                                transaction = RpcTransaction(
                                        hash = etherscanTransaction.hash,
                                        nonce = etherscanTransaction.nonce,
                                        blockHash = etherscanTransaction.blockHash,
                                        blockNumber = etherscanTransaction.blockNumber,
                                        transactionIndex = etherscanTransaction.transactionIndex,
                                        from = etherscanTransaction.from,
                                        to = etherscanTransaction.to,
                                        value = etherscanTransaction.value,
                                        gasPrice = etherscanTransaction.gasPrice,
                                        gasLimit = etherscanTransaction.gasLimit,
                                        input = etherscanTransaction.input
                                ),
                                timestamp = etherscanTransaction.timestamp
                        )
                    }
                }
                .subscribeOn(Schedulers.io())
                .subscribe({ notSyncedTransactions ->
                    logger.info("---> sync() onFetched: ${notSyncedTransactions.size}")

                    handle(notSyncedTransactions)

                    state = EthereumKit.SyncState.Synced()
                }, {
                    logger.info("---> sync() onError: ${it.message}")

                    state = EthereumKit.SyncState.NotSynced(it)
                })
                .let { disposables.add(it) }

    }


    private fun handle(notSyncedTransactions: List<NotSyncedTransaction>) {
        if (notSyncedTransactions.isNotEmpty()) {
            delegate.add(notSyncedTransactions)

            notSyncedTransactions.firstOrNull()?.transaction?.blockNumber?.let {
                lastSyncBlockNumber = it
            }
        }
    }

}
