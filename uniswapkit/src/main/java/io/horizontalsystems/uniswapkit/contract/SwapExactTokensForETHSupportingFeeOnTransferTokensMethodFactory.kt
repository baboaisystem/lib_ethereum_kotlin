package com.baboaisystem.uniswapkit.contract

import com.baboaisystem.ethereumkit.contracts.ContractMethod
import com.baboaisystem.ethereumkit.contracts.ContractMethodFactory
import com.baboaisystem.ethereumkit.contracts.ContractMethodHelper
import com.baboaisystem.ethereumkit.models.Address
import java.math.BigInteger

object SwapExactTokensForETHSupportingFeeOnTransferTokensMethodFactory : ContractMethodFactory {
    override val methodId = ContractMethodHelper.getMethodId(SwapExactTokensForETHMethod.methodSignature)

    override fun createMethod(inputArguments: ByteArray): ContractMethod {
        val parsedArguments = ContractMethodHelper.decodeABI(inputArguments, listOf(BigInteger::class, BigInteger::class, List::class, Address::class, BigInteger::class))
        val amountIn = parsedArguments[0] as BigInteger
        val amountOutMin = parsedArguments[1] as BigInteger
        val path = parsedArguments[2] as List<Address>
        val to = parsedArguments[3] as Address
        val deadline = parsedArguments[4] as BigInteger
        return SwapExactTokensForETHMethod(amountIn, amountOutMin, path, to, deadline)
    }

}
