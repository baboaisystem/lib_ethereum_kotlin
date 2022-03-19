package com.baboaisystem.ethereumkit.api.core

import com.baboaisystem.ethereumkit.api.jsonrpc.BlockNumberJsonRpc
import com.baboaisystem.ethereumkit.api.jsonrpc.JsonRpc
import com.baboaisystem.ethereumkit.core.EthereumKit
import com.baboaisystem.ethereumkit.network.ConnectionManager
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.schedule

class ApiRpcSyncer(
        private val rpcApiProvider: IRpcApiProvider,
        private val connectionManager: ConnectionManager
) : IRpcSyncer {
    private val disposables = CompositeDisposable()
    private var isStarted = false
    private var currentRpcId = AtomicInteger(0)
    private var timer: Timer? = null

    init {
        connectionManager.listener = object : ConnectionManager.Listener {
            override fun onConnectionChange() {
                handleConnectionChange()
            }
        }
    }

    //region IRpcSyncer
    override var listener: IRpcSyncerListener? = null
    override val source = "API ${rpcApiProvider.source}"
    override var state: SyncerState = SyncerState.NotReady(EthereumKit.SyncError.NotStarted())
        private set(value) {
            if (value != field) {
                field = value
                listener?.didUpdateSyncerState(value)
            }
        }

    override fun start() {
        isStarted = true

        handleConnectionChange()
    }

    override fun stop() {
        isStarted = false

        state = SyncerState.NotReady(EthereumKit.SyncError.NotStarted())
        disposables.clear()
        stopTimer()
    }

    override fun <T> single(rpc: JsonRpc<T>): Single<T> {
        rpc.id = currentRpcId.addAndGet(1)
        return rpcApiProvider.single(rpc)
    }
    //endregion

    private fun handleConnectionChange() {
        if (!isStarted) return

        if (connectionManager.isConnected) {
            state = SyncerState.Ready
            startTimer()
        } else {
            state = SyncerState.NotReady(EthereumKit.SyncError.NoNetworkConnection())
            stopTimer()
        }
    }

    private fun startTimer() {
        timer = Timer().apply {
            schedule(0, rpcApiProvider.blockTime * 1000) {
                onFireTimer()
            }
        }
    }

    private fun stopTimer() {
        timer?.cancel()
        timer = null
    }

    private fun onFireTimer() {
        rpcApiProvider.single(BlockNumberJsonRpc())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({ lastBlockNumber ->
                    listener?.didUpdateLastBlockHeight(lastBlockNumber)
                }, {
                    state = SyncerState.NotReady(it)
                }).let {
                    disposables.add(it)
                }
    }

}
