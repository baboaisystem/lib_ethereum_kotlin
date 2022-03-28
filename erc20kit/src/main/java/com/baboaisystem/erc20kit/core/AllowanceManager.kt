package com.baboaisystem.erc20kit.core

import com.baboaisystem.erc20kit.contract.AllowanceMethod
import com.baboaisystem.erc20kit.contract.ApproveMethod
import com.baboaisystem.ethereumkit.core.EthereumKit
import com.baboaisystem.ethereumkit.models.Address
import com.baboaisystem.ethereumkit.models.DefaultBlockParameter
import com.baboaisystem.ethereumkit.models.TransactionData
import io.reactivex.Single
import java.math.BigInteger

class AllowanceManager(
        private val ethereumKit: EthereumKit,
        private val contractAddress: Address,
        private val address: Address
) {

    fun allowance(spenderAddress: Address, defaultBlockParameter: DefaultBlockParameter): Single<BigInteger> {
        return ethereumKit
                .call(contractAddress, AllowanceMethod(address, spenderAddress).encodedABI(), defaultBlockParameter)
                .map { result ->
                    BigInteger(result.sliceArray(0..31))
                }
    }

    fun approveTransactionData(spenderAddress: Address, amount: BigInteger): TransactionData {
        return TransactionData(contractAddress, BigInteger.ZERO, ApproveMethod(spenderAddress, amount).encodedABI())
    }

}
