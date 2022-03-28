package com.baboaisystem.ethereumkit.transactionsyncers

import com.baboaisystem.ethereumkit.core.INotSyncedTransactionPool
import com.baboaisystem.ethereumkit.core.ITransactionSyncerDelegate
import com.baboaisystem.ethereumkit.core.ITransactionSyncerStateStorage
import com.baboaisystem.ethereumkit.models.NotSyncedTransaction
import com.baboaisystem.ethereumkit.models.TransactionSyncerState
import io.reactivex.Flowable

class NotSyncedTransactionManager(
        private val pool: INotSyncedTransactionPool,
        private val storage: ITransactionSyncerStateStorage
) : ITransactionSyncerDelegate {

    override val notSyncedTransactionsSignal: Flowable<Unit>
        get() = pool.notSyncedTransactionsSignal

    override fun getNotSyncedTransactions(limit: Int): List<NotSyncedTransaction> {
        return pool.getNotSyncedTransactions(limit)
    }

    override fun add(notSyncedTransactions: List<NotSyncedTransaction>) {
        pool.add(notSyncedTransactions)
    }

    override fun remove(notSyncedTransaction: NotSyncedTransaction) {
        pool.remove(notSyncedTransaction)
    }

    override fun update(notSyncedTransaction: NotSyncedTransaction) {
        pool.update(notSyncedTransaction)
    }

    override fun getTransactionSyncerState(id: String): TransactionSyncerState? {
        return storage.getTransactionSyncerState(id)
    }

    override fun update(transactionSyncerState: TransactionSyncerState) {
        storage.save(transactionSyncerState)
    }

}
