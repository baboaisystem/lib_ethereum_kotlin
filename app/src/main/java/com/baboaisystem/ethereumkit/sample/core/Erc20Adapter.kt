package com.baboaisystem.ethereumkit.sample.core

import android.content.Context
import com.baboaisystem.ethereumkit.core.EthereumKit
import com.baboaisystem.ethereumkit.core.signer.Signer
import com.baboaisystem.ethereumkit.models.Address
import com.baboaisystem.ethereumkit.models.FullTransaction
import com.baboaisystem.ethereumkit.models.GasPrice
import com.baboaisystem.ethereumkit.sample.modules.main.Erc20Token
import io.reactivex.Single
import java.math.BigDecimal

class Erc20Adapter(
    context: Context,
    token: Erc20Token,
    private val ethereumKit: EthereumKit,
    private val signer: Signer
) : Erc20BaseAdapter(context, token, ethereumKit) {

    override fun send(address: Address, amount: BigDecimal, gasPrice: GasPrice, gasLimit: Long): Single<FullTransaction> {
        val valueBigInteger = amount.movePointRight(decimals).toBigInteger()
        val transactionData = erc20Kit.buildTransferTransactionData(address, valueBigInteger)

        return ethereumKit
            .rawTransaction(transactionData, gasPrice, gasLimit)
            .flatMap { rawTransaction ->
                val signature = signer.signature(rawTransaction)
                ethereumKit.send(rawTransaction, signature)
            }
    }

    fun allowance(spenderAddress: Address): Single<BigDecimal> {
        return erc20Kit.getAllowanceAsync(spenderAddress).map { allowance -> allowance.toBigDecimal().movePointLeft(decimals) }
    }

}
