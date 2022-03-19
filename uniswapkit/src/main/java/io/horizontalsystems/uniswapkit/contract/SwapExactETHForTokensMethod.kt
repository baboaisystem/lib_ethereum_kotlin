package com.baboaisystem.uniswapkit.contract

import com.baboaisystem.ethereumkit.contracts.ContractMethod
import com.baboaisystem.ethereumkit.models.Address
import java.math.BigInteger

class SwapExactETHForTokensMethod(
        val amountOutMin: BigInteger,
        val path: List<Address>,
        val to: Address,
        val deadline: BigInteger
) : ContractMethod() {

    override val methodSignature = Companion.methodSignature
    override fun getArguments() = listOf(amountOutMin, path, to, deadline)

    companion object {
        const val methodSignature = "swapExactETHForTokens(uint256,address[],address,uint256)"
    }
}
