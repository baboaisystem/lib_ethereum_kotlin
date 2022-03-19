package com.baboaisystem.ethereumkit.spv.core

import com.baboaisystem.ethereumkit.core.TransactionBuilder
import com.baboaisystem.ethereumkit.models.Transaction
import com.baboaisystem.ethereumkit.models.RawTransaction
import com.baboaisystem.ethereumkit.models.Signature
import com.baboaisystem.ethereumkit.spv.net.handlers.SendTransactionTaskHandler
import com.baboaisystem.ethereumkit.spv.net.tasks.SendTransactionTask

class TransactionSender(
        private val transactionBuilder: TransactionBuilder,
) : SendTransactionTaskHandler.Listener {

    interface Listener {
        fun onSendSuccess(sendId: Int, transaction: Transaction)
        fun onSendFailure(sendId: Int, error: Throwable)
    }

    var listener: Listener? = null

    fun send(sendId: Int, taskPerformer: ITaskPerformer, rawTransaction: RawTransaction, signature: Signature) {
        taskPerformer.add(SendTransactionTask(sendId, rawTransaction, signature))
    }

    override fun onSendSuccess(task: SendTransactionTask) {
        val transaction = transactionBuilder.transaction(task.rawTransaction, task.signature)

        listener?.onSendSuccess(task.sendId, transaction)
    }

    override fun onSendFailure(task: SendTransactionTask, error: Throwable) {
        listener?.onSendFailure(task.sendId, error)
    }

}
