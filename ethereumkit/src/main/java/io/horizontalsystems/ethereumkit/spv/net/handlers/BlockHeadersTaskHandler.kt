package com.baboaisystem.ethereumkit.spv.net.handlers

import com.baboaisystem.ethereumkit.spv.core.*
import com.baboaisystem.ethereumkit.spv.helpers.RandomHelper
import com.baboaisystem.ethereumkit.spv.models.BlockHeader
import com.baboaisystem.ethereumkit.spv.net.IInMessage
import com.baboaisystem.ethereumkit.spv.net.les.messages.BlockHeadersMessage
import com.baboaisystem.ethereumkit.spv.net.les.messages.GetBlockHeadersMessage
import com.baboaisystem.ethereumkit.spv.net.tasks.BlockHeadersTask

class BlockHeadersTaskHandler(private val listener: Listener? = null) : ITaskHandler, IMessageHandler {

    interface Listener {
        fun didReceive(peer: IPeer, blockHeaders: List<BlockHeader>, blockHeader: BlockHeader, reverse: Boolean)
    }

    private val tasks: MutableMap<Long, BlockHeadersTask> = HashMap()

    override fun perform(task: ITask, requester: ITaskHandlerRequester): Boolean {
        if (task !is BlockHeadersTask) {
            return false
        }

        val requestId = RandomHelper.randomLong()

        tasks[requestId] = task

        val message = GetBlockHeadersMessage(requestID = requestId,
                blockHeight = task.blockHeader.height, maxHeaders = task.limit,
                reverse = if (task.reverse) 1 else 0)

        requester.send(message)

        return true
    }

    override fun handle(peer: IPeer, message: IInMessage): Boolean {
        if (message !is BlockHeadersMessage) {
            return false
        }

        val task = tasks[message.requestID] ?: return false

        listener?.didReceive(peer, message.headers, task.blockHeader, task.reverse)

        return true
    }

}
