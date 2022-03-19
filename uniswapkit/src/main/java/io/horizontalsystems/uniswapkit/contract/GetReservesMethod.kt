package com.baboaisystem.uniswapkit.contract

import com.baboaisystem.ethereumkit.contracts.ContractMethod

class GetReservesMethod : ContractMethod() {

    override val methodSignature = "getReserves()"
    override fun getArguments() = listOf<Any>()

}
