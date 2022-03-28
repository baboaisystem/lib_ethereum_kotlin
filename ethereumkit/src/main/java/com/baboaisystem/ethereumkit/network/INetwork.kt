package com.baboaisystem.ethereumkit.network

import com.baboaisystem.ethereumkit.spv.models.BlockHeader

interface INetwork {
    val id: Int
    val genesisBlockHash: ByteArray
    val checkpointBlock: BlockHeader
    val blockTime: Long
}