package com.baboaisystem.uniswapkit.contract

import com.baboaisystem.ethereumkit.contracts.ContractMethod
import com.baboaisystem.ethereumkit.models.Address
import java.math.BigInteger

class SwapETHForExactTokensMethod(
        val amountOut: BigInteger,
        val path: List<Address>,
        val to: Address,
        val deadline: BigInteger
) : ContractMethod() {

    override val methodSignature = Companion.methodSignature
    override fun getArguments() = listOf(amountOut, path, to, deadline)

    companion object {
        const val methodSignature = "swapETHForExactTokens(uint256,address[],address,uint256)"
    }

}
