package com.baboaisystem.uniswapkit.contract

import com.baboaisystem.ethereumkit.contracts.ContractMethod
import com.baboaisystem.ethereumkit.contracts.ContractMethodFactory
import com.baboaisystem.ethereumkit.contracts.ContractMethodHelper
import com.baboaisystem.ethereumkit.models.Address
import java.math.BigInteger

object SwapExactETHForTokensMethodFactory : ContractMethodFactory {

    override val methodId = ContractMethodHelper.getMethodId(SwapExactETHForTokensMethod.methodSignature)

    override fun createMethod(inputArguments: ByteArray): ContractMethod {
        val parsedArguments = ContractMethodHelper.decodeABI(inputArguments, listOf(BigInteger::class, List::class, Address::class, BigInteger::class))
        val amountOutMin = parsedArguments[0] as BigInteger
        val path = parsedArguments[1] as List<Address>
        val to = parsedArguments[2] as Address
        val deadline = parsedArguments[3] as BigInteger
        return SwapExactETHForTokensMethod(amountOutMin, path, to, deadline)
    }

}
