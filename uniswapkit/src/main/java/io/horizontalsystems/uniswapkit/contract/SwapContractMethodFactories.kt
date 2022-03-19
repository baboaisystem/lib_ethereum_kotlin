package com.baboaisystem.uniswapkit.contract

import com.baboaisystem.ethereumkit.contracts.ContractMethodFactories

object SwapContractMethodFactories : ContractMethodFactories() {
    init {
        val swapContractMethodFactories = listOf(
                SwapETHForExactTokensMethodFactory,
                SwapExactETHForTokensMethodFactory,
                SwapExactETHForTokensSupportingFeeOnTransferTokensMethodFactory,
                SwapExactTokensForETHMethodFactory,
                SwapExactTokensForETHSupportingFeeOnTransferTokensMethodFactory,
                SwapExactTokensForTokensMethodFactory,
                SwapExactTokensForTokensSupportingFeeOnTransferTokensMethodFactory,
                SwapTokensForExactETHMethodFactory,
                SwapTokensForExactTokensMethodFactory
        )
        registerMethodFactories(swapContractMethodFactories)
    }
}
