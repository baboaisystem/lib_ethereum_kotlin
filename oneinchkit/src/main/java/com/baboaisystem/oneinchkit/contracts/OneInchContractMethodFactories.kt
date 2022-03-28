package com.baboaisystem.oneinchkit.contracts

import com.baboaisystem.ethereumkit.contracts.ContractMethodFactories

object OneInchContractMethodFactories : ContractMethodFactories() {

    init {
        val swapContractMethodFactories = listOf(UnoswapMethodFactory(), SwapMethodFactory())
        registerMethodFactories(swapContractMethodFactories)
    }

}
