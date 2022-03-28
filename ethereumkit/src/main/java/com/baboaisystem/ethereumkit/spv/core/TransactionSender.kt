package com.baboaisystem.ethereumkit.spv.core

import com.baboaisystem.ethereumkit.core.TransactionBuilder
import com.baboaisystem.ethereumkit.core.TransactionSigner
import com.baboaisystem.ethereumkit.models.EtherscanTransaction
import com.baboaisystem.ethereumkit.models.Transaction
import com.baboaisystem.ethereumkit.spv.models.RawTransaction
import com.baboaisystem.ethereumkit.spv.net.handlers.SendTransactionTaskHandler
import com.baboaisystem.ethereumkit.spv.net.tasks.SendTransactionTask

class TransactionSender(
        private val transactionBuilder: TransactionBuilder,
        private val transactionSigner: TransactionSigner
) : SendTransactionTaskHandler.Listener {

    interface Listener {
        fun onSendSuccess(sendId: Int, transaction: Transaction)
        fun onSendFailure(sendId: Int, error: Throwable)
    }

    var listener: Listener? = null

    fun send(sendId: Int, taskPerformer: ITaskPerformer, rawTransaction: RawTransaction) {
        val signature = transactionSigner.signature(rawTransaction)

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
