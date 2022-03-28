package com.baboaisystem.erc20kit.core

import com.baboaisystem.erc20kit.contract.BalanceOfMethod
import com.baboaisystem.ethereumkit.core.EthereumKit
import com.baboaisystem.ethereumkit.models.Address
import com.baboaisystem.ethereumkit.spv.core.toBigInteger
import io.reactivex.Single
import java.math.BigInteger

class DataProvider(
        private val ethereumKit: EthereumKit
) : IDataProvider {

    override fun getBalance(contractAddress: Address, address: Address): Single<BigInteger> {
        return ethereumKit.call(contractAddress, BalanceOfMethod(address).encodedABI())
                .map { it.toBigInteger() }
    }

}
