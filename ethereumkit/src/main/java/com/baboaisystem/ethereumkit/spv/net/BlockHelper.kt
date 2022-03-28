package com.baboaisystem.ethereumkit.spv.net

import com.baboaisystem.ethereumkit.core.ISpvStorage
import com.baboaisystem.ethereumkit.network.INetwork
import com.baboaisystem.ethereumkit.spv.models.BlockHeader

class BlockHelper(val storage: ISpvStorage, val network: INetwork) {

    val lastBlockHeader: BlockHeader
        get() = storage.getLastBlockHeader() ?: network.checkpointBlock

}
