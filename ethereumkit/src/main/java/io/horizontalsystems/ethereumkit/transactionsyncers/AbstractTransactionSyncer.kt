package com.baboaisystem.ethereumkit.transactionsyncers

import com.baboaisystem.ethereumkit.api.models.AccountState
import com.baboaisystem.ethereumkit.core.EthereumKit
import com.baboaisystem.ethereumkit.core.ITransactionSyncer
import com.baboaisystem.ethereumkit.core.ITransactionSyncerDelegate
import com.baboaisystem.ethereumkit.models.BloomFilter
import com.baboaisystem.ethereumkit.models.TransactionSyncerState
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

abstract class AbstractTransactionSyncer(
        override val id: String
) : ITransactionSyncer {

    private val stateSubject = PublishSubject.create<EthereumKit.SyncState>()
    protected val disposables = CompositeDisposable()

    override var state: EthereumKit.SyncState = EthereumKit.SyncState.NotSynced(EthereumKit.SyncError.NotStarted())
        protected set(value) {
            field = value
            stateSubject.onNext(value)
        }
    override val stateAsync: Flowable<EthereumKit.SyncState>
        get() = stateSubject.toFlowable(BackpressureStrategy.BUFFER)

    protected lateinit var delegate: ITransactionSyncerDelegate

    protected var lastSyncBlockNumber: Long
        get() = delegate.getTransactionSyncerState(id)?.lastBlockNumber ?: 0
        set(value) {
            delegate.update(TransactionSyncerState(id, value))
        }

    override fun start() {}

    override fun stop() {
        disposables.clear()
    }

    override fun onEthereumKitSynced() {}

    override fun onLastBlockBloomFilter(bloomFilter: BloomFilter) {}

    override fun onUpdateAccountState(accountState: AccountState) {}

    override fun onLastBlockNumber(blockNumber: Long) {}

    override fun set(delegate: ITransactionSyncerDelegate) {
        this.delegate = delegate
    }

}
