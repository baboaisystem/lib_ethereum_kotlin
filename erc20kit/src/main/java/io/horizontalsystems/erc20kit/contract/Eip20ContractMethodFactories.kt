package com.baboaisystem.erc20kit.contract

import com.baboaisystem.ethereumkit.contracts.ContractMethodFactories

object Eip20ContractMethodFactories : ContractMethodFactories() {

    init {
        registerMethodFactories(listOf(TransferMethodFactory, ApproveMethodFactory))
    }

}
