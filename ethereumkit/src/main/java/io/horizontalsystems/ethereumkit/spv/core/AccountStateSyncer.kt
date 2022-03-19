package com.baboaisystem.ethereumkit.spv.core

import com.baboaisystem.ethereumkit.core.ISpvStorage
import com.baboaisystem.ethereumkit.models.Address
import com.baboaisystem.ethereumkit.spv.models.AccountStateSpv
import com.baboaisystem.ethereumkit.spv.models.BlockHeader
import com.baboaisystem.ethereumkit.spv.net.handlers.AccountStateTaskHandler
import com.baboaisystem.ethereumkit.spv.net.tasks.AccountStateTask

class AccountStateSyncer(private val storage: ISpvStorage,
                         private val address: Address) : AccountStateTaskHandler.Listener {

    interface Listener {
        fun onUpdate(accountState: AccountStateSpv)
    }

    var listener: Listener? = null

    fun sync(taskPerformer: ITaskPerformer, blockHeader: BlockHeader) {
        taskPerformer.add(AccountStateTask(address, blockHeader))
    }

    override fun didReceive(accountState: AccountStateSpv, address: Address, blockHeader: BlockHeader) {
        storage.saveAccountSate(accountState)
        listener?.onUpdate(accountState)
    }

}
