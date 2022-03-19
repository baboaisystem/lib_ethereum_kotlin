package com.baboaisystem.erc20kit.decorations

import com.baboaisystem.ethereumkit.contracts.ContractEvent
import com.baboaisystem.ethereumkit.decorations.ContractEventDecoration
import com.baboaisystem.ethereumkit.models.Address
import com.baboaisystem.ethereumkit.models.TransactionTag
import java.math.BigInteger

class ApproveEventDecoration(
        contractAddress: Address, val owner: Address, val spender: Address, val value: BigInteger
) : ContractEventDecoration(contractAddress) {

    override fun tags(fromAddress: Address, toAddress: Address, userAddress: Address): List<String> {
        return listOf()
    }

    companion object {
        val signature = ContractEvent(
                "Approval",
                listOf(
                        ContractEvent.Argument.Address,
                        ContractEvent.Argument.Address,
                        ContractEvent.Argument.Uint256
                )
        ).signature
    }
}
