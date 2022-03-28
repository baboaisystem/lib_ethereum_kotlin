package com.baboaisystem.erc20kit.contract

import com.baboaisystem.ethereumkit.contracts.ContractMethodFactory
import com.baboaisystem.ethereumkit.contracts.ContractMethodHelper
import com.baboaisystem.ethereumkit.models.Address
import com.baboaisystem.ethereumkit.spv.core.toBigInteger

object TransferMethodFactory : ContractMethodFactory {

    override val methodId = ContractMethodHelper.getMethodId(TransferMethod.methodSignature)

    override fun createMethod(inputArguments: ByteArray): TransferMethod {
        val address = Address(inputArguments.copyOfRange(12, 32))
        val value = inputArguments.copyOfRange(32, 64).toBigInteger()

        return TransferMethod(address, value)
    }

}
