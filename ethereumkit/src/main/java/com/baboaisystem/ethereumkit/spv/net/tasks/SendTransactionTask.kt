package com.baboaisystem.ethereumkit.spv.net.tasks

import com.baboaisystem.ethereumkit.spv.core.ITask
import com.baboaisystem.ethereumkit.spv.models.RawTransaction
import com.baboaisystem.ethereumkit.spv.models.Signature

class SendTransactionTask(val sendId: Int,
                          val rawTransaction: RawTransaction,
                          val signature: Signature) : ITask
