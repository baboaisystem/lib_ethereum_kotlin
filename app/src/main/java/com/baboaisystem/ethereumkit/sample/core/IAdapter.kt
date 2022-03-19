package com.baboaisystem.ethereumkit.sample.core

import com.baboaisystem.ethereumkit.core.EthereumKit
import com.baboaisystem.ethereumkit.models.Address
import com.baboaisystem.ethereumkit.models.FullTransaction
import com.baboaisystem.ethereumkit.models.GasPrice
import io.reactivex.Flowable
import io.reactivex.Single
import java.math.BigDecimal

interface IAdapter {

    val name: String
    val coin: String

    val lastBlockHeight: Long?
    val syncState: EthereumKit.SyncState
    val transactionsSyncState: EthereumKit.SyncState
    val balance: BigDecimal

    val receiveAddress: Address

    val lastBlockHeightFlowable: Flowable<Unit>
    val syncStateFlowable: Flowable<Unit>
    val transactionsSyncStateFlowable: Flowable<Unit>
    val balanceFlowable: Flowable<Unit>
    val transactionsFlowable: Flowable<Unit>

    fun start()
    fun stop()
    fun refresh()
    fun send(address: Address, amount: BigDecimal, gasPrice: GasPrice, gasLimit: Long): Single<FullTransaction>
    fun transactions(fromHash: ByteArray? = null, limit: Int? = null): Single<List<TransactionRecord>>

    fun estimatedGasLimit(toAddress: Address, value: BigDecimal, gasPrice: GasPrice): Single<Long>
}
