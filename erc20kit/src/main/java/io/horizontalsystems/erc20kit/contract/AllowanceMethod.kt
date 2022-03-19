package com.baboaisystem.erc20kit.contract

import com.baboaisystem.ethereumkit.contracts.ContractMethod
import com.baboaisystem.ethereumkit.models.Address

class AllowanceMethod(val owner: Address, val spender: Address) : ContractMethod() {

    override val methodSignature = "allowance(address,address)"
    override fun getArguments() = listOf(owner, spender)

}
