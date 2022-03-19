package com.baboaisystem.oneinchkit.decorations

import com.baboaisystem.ethereumkit.models.Address
import com.baboaisystem.ethereumkit.models.TransactionTag

class OneInchV4MethodDecoration : OneInchMethodDecoration() {
    override fun tags(fromAddress: Address, toAddress: Address, userAddress: Address): List<String> {
        return mutableListOf(toAddress.hex, TransactionTag.SWAP)
    }
}
