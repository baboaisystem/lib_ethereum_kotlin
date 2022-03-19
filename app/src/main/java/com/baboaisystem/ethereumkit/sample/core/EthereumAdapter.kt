package com.baboaisystem.ethereumkit.sample.core

import com.baboaisystem.ethereumkit.core.EthereumKit
import com.baboaisystem.ethereumkit.core.signer.Signer
import com.baboaisystem.ethereumkit.models.Address
import com.baboaisystem.ethereumkit.models.FullTransaction
import com.baboaisystem.ethereumkit.models.GasPrice
import io.reactivex.Single
import java.math.BigDecimal

class EthereumAdapter(
        private val ethereumKit: EthereumKit,
        private val signer: Signer
) : EthereumBaseAdapter(ethereumKit) {

    private val decimal = 18

    override fun send(
            address: Address,
            amount: BigDecimal,
            gasPrice: GasPrice,
            gasLimit: Long
    ): Single<FullTransaction> {
        val amountBigInt = amount.movePointRight(decimal).toBigInteger()
        val transactionData = ethereumKit.transferTransactionData(address, amountBigInt)
        return ethereumKit.rawTransaction(transactionData, gasPrice, gasLimit)
                .flatMap { rawTransaction ->
                    val signature = signer.signature(rawTransaction)
                    ethereumKit.send(rawTransaction, signature)
                }
    }

}
