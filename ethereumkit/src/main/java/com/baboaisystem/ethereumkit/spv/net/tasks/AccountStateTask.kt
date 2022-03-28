package com.baboaisystem.ethereumkit.spv.net.tasks

import com.baboaisystem.ethereumkit.models.Address
import com.baboaisystem.ethereumkit.spv.core.ITask
import com.baboaisystem.ethereumkit.spv.models.BlockHeader

class AccountStateTask(val address: Address, val blockHeader: BlockHeader) : ITask
