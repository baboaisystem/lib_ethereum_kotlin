package com.baboaisystem.oneinchkit.contracts

import com.baboaisystem.ethereumkit.contracts.Bytes32Array
import com.baboaisystem.ethereumkit.contracts.ContractMethod
import com.baboaisystem.ethereumkit.models.Address
import java.math.BigInteger

class UnoswapMethod(
        val srcToken: Address,
        val amount: BigInteger,
        val minReturn: BigInteger,
        val params: Bytes32Array
) : ContractMethod() {

    override val methodSignature = Companion.methodSignature

    override fun getArguments() = listOf(srcToken, amount, minReturn, params)

    companion object {
        val methodSignature = "unoswap(address,uint256,uint256,bytes32[])"
    }

}
