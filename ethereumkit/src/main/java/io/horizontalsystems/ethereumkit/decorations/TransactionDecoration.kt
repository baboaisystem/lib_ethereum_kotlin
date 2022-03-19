package com.baboaisystem.ethereumkit.decorations

import com.baboaisystem.ethereumkit.models.Address

abstract class TransactionDecoration {
    abstract fun tags(fromAddress: Address, toAddress: Address, userAddress: Address): List<String>
}
