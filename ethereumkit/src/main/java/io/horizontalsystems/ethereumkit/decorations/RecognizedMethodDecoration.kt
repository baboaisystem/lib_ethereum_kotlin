package com.baboaisystem.ethereumkit.decorations

import com.baboaisystem.ethereumkit.models.Address

class RecognizedMethodDecoration(val method: String, val arguments: List<Any>): ContractMethodDecoration() {

    override fun tags(fromAddress: Address, toAddress: Address, userAddress: Address): List<String> {
        return listOf(toAddress.hex, method)
    }
}
