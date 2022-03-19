package com.baboaisystem.uniswapkit

import com.baboaisystem.ethereumkit.core.ITransactionWatcher
import com.baboaisystem.ethereumkit.models.Address
import com.baboaisystem.ethereumkit.models.FullTransaction
import com.baboaisystem.uniswapkit.decorations.SwapMethodDecoration

class UniswapTransactionWatcher(
        private val address: Address
) : ITransactionWatcher {

    override fun needInternalTransactions(fullTransaction: FullTransaction): Boolean {
        val decoration = fullTransaction.mainDecoration

        // need internal transaction to get actual amountOut for the case when swapped tokenOut is ETH and recipient is different address
        return fullTransaction.internalTransactions.isEmpty() &&
                decoration is SwapMethodDecoration &&
                decoration.trade is SwapMethodDecoration.Trade.ExactIn &&
                decoration.tokenOut == SwapMethodDecoration.Token.EvmCoin &&
                fullTransaction.transaction.from == address &&
                decoration.to != address

    }

}
