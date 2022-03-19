package com.baboaisystem.ethereumkit.decorations

import com.baboaisystem.ethereumkit.models.Address

abstract class ContractEventDecoration(val contractAddress: Address): TransactionDecoration()
