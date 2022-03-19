package com.baboaisystem.erc20kit.contract

import com.baboaisystem.ethereumkit.contracts.ContractMethod
import com.baboaisystem.ethereumkit.models.Address

class BalanceOfMethod(val owner: Address) : ContractMethod() {

    override val methodSignature = "balanceOf(address)"
    override fun getArguments() = listOf(owner)

}
