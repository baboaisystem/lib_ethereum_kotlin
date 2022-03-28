package com.baboaisystem.ethereumkit.contracts

interface ContractMethodFactory {

    val methodId: ByteArray
    fun createMethod(inputArguments: ByteArray): ContractMethod

}
