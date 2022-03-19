package com.baboaisystem.ethereumkit.models

import com.baboaisystem.ethereumkit.api.jsonrpc.models.RpcTransaction

class NotSyncedTransaction(
        val hash: ByteArray,
        var transaction: RpcTransaction? = null,
        val timestamp: Long? = null
)
