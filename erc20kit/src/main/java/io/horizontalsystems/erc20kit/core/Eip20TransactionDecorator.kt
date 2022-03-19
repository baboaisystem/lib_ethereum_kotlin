package com.baboaisystem.erc20kit.core

import com.baboaisystem.erc20kit.contract.ApproveMethod
import com.baboaisystem.erc20kit.contract.Eip20ContractMethodFactories
import com.baboaisystem.erc20kit.contract.TransferMethod
import com.baboaisystem.erc20kit.decorations.ApproveEventDecoration
import com.baboaisystem.erc20kit.decorations.ApproveMethodDecoration
import com.baboaisystem.erc20kit.decorations.TransferEventDecoration
import com.baboaisystem.erc20kit.decorations.TransferMethodDecoration
import com.baboaisystem.ethereumkit.core.IDecorator
import com.baboaisystem.ethereumkit.decorations.ContractEventDecoration
import com.baboaisystem.ethereumkit.decorations.ContractMethodDecoration
import com.baboaisystem.ethereumkit.models.Address
import com.baboaisystem.ethereumkit.models.FullTransaction
import com.baboaisystem.ethereumkit.models.TransactionData
import com.baboaisystem.ethereumkit.models.TransactionLog


class Eip20TransactionDecorator(
        private val userAddress: Address,
        private val contractMethodFactories: Eip20ContractMethodFactories
) : IDecorator {

    override fun decorate(transactionData: TransactionData, fullTransaction: FullTransaction?): ContractMethodDecoration? =
            when (val contractMethod = contractMethodFactories.createMethodFromInput(transactionData.input)) {
                is TransferMethod -> TransferMethodDecoration(contractMethod.to, contractMethod.value)
                is ApproveMethod -> ApproveMethodDecoration(contractMethod.spender, contractMethod.value)
                else -> null
            }

    override fun decorate(logs: List<TransactionLog>): List<ContractEventDecoration> {
        return logs.mapNotNull { log ->

            val event = log.getErc20Event() ?: return@mapNotNull null

            when (event) {
                is TransferEventDecoration -> {
                    if (event.from == userAddress || event.to == userAddress) {
                        log.relevant = true
                        return@mapNotNull event
                    }
                }
                is ApproveEventDecoration -> {
                    if (event.owner == userAddress || event.spender == userAddress) {
                        log.relevant = true
                        return@mapNotNull event
                    }
                }
            }

            return@mapNotNull null
        }
    }
}
