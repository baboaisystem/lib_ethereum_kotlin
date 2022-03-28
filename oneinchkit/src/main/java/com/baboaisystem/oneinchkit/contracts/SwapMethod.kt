package com.baboaisystem.oneinchkit.contracts

import com.baboaisystem.ethereumkit.contracts.ContractMethod
import com.baboaisystem.ethereumkit.models.Address
import java.math.BigInteger

class SwapMethod(
        val caller: Address,
        val swapDescription: SwapDescription,
        val data: ByteArray
) : ContractMethod() {

    override val methodSignature = Companion.methodSignature

    override fun getArguments() = listOf(caller, swapDescription, data)

    data class SwapDescription(
            val srcToken: Address,
            val dstToken: Address,
            val srcReceiver: Address,
            val dstReceiver: Address,
            val amount: BigInteger,
            val minReturnAmount: BigInteger,
            val flags: BigInteger,
            val permit: ByteArray
    )

    companion object {
        val methodSignature = "swap(address,(address,address,address,address,uint256,uint256,uint256,bytes),bytes)"
    }

}
