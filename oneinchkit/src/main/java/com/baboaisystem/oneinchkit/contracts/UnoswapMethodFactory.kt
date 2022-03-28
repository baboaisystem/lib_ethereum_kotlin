package com.baboaisystem.oneinchkit.contracts

import com.baboaisystem.ethereumkit.contracts.Bytes32Array
import com.baboaisystem.ethereumkit.contracts.ContractMethod
import com.baboaisystem.ethereumkit.contracts.ContractMethodFactory
import com.baboaisystem.ethereumkit.contracts.ContractMethodHelper
import com.baboaisystem.ethereumkit.models.Address
import java.math.BigInteger

class UnoswapMethodFactory : ContractMethodFactory {

    override val methodId = ContractMethodHelper.getMethodId(UnoswapMethod.methodSignature)

    override fun createMethod(inputArguments: ByteArray): ContractMethod {
        val argumentTypes = listOf(Address::class, BigInteger::class, BigInteger::class, Bytes32Array::class)

        val parsedArguments = ContractMethodHelper.decodeABI(inputArguments, argumentTypes)

        val srcToken = parsedArguments[0] as Address
        val amount = parsedArguments[1] as BigInteger
        val minReturn = parsedArguments[2] as BigInteger
        val params = parsedArguments[3] as Bytes32Array

        return UnoswapMethod(srcToken, amount, minReturn, params)
    }

}
