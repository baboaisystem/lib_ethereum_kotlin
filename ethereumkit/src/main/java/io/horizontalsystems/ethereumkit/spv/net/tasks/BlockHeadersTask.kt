package com.baboaisystem.ethereumkit.spv.net.tasks

import com.baboaisystem.ethereumkit.spv.core.ITask
import com.baboaisystem.ethereumkit.spv.models.BlockHeader

class BlockHeadersTask(val blockHeader: BlockHeader, val limit: Int, val reverse: Boolean = false) : ITask