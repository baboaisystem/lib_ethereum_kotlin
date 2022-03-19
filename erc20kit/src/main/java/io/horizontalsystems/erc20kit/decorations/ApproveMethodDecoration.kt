package com.baboaisystem.erc20kit.decorations

import com.baboaisystem.ethereumkit.decorations.ContractMethodDecoration
import com.baboaisystem.ethereumkit.models.Address
import com.baboaisystem.ethereumkit.models.TransactionTag
import java.math.BigInteger

class ApproveMethodDecoration(val spender: Address, val value: BigInteger) : ContractMethodDecoration() {

    override fun tags(fromAddress: Address, toAddress: Address, userAddress: Address): List<String> {
        return listOf(toAddress.hex, TransactionTag.EIP20_APPROVE)
    }
}
