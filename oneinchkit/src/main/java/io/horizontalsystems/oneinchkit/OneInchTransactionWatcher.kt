package com.baboaisystem.oneinchkit

import com.baboaisystem.ethereumkit.core.ITransactionWatcher
import com.baboaisystem.ethereumkit.models.Address
import com.baboaisystem.ethereumkit.models.FullTransaction
import com.baboaisystem.oneinchkit.decorations.OneInchMethodDecoration
import com.baboaisystem.oneinchkit.decorations.OneInchSwapMethodDecoration

class OneInchTransactionWatcher(
        private val address: Address
) : ITransactionWatcher {

    override fun needInternalTransactions(fullTransaction: FullTransaction): Boolean {
        val decoration = fullTransaction.mainDecoration

        // need internal transaction to get actual toAmount for the case when swapped toToken is ETH and recipient is different address
        return fullTransaction.internalTransactions.isEmpty() &&
                decoration is OneInchSwapMethodDecoration &&
                decoration.toToken == OneInchMethodDecoration.Token.EvmCoin &&
                fullTransaction.transaction.from == address &&
                decoration.recipient != address

    }

}
