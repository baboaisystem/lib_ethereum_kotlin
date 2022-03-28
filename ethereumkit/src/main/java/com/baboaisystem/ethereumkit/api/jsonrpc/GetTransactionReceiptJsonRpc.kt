package com.baboaisystem.ethereumkit.api.jsonrpc

import com.google.gson.reflect.TypeToken
import com.baboaisystem.ethereumkit.api.jsonrpc.models.RpcTransaction
import com.baboaisystem.ethereumkit.api.jsonrpc.models.RpcTransactionReceipt
import java.lang.reflect.Type
import java.util.*

class GetTransactionReceiptJsonRpc(
        @Transient val transactionHash: ByteArray
) : JsonRpc<Optional<RpcTransactionReceipt>>(
        method = "eth_getTransactionReceipt",
        params = listOf(transactionHash)
) {
    @Transient
    override val typeOfResult: Type = object : TypeToken<Optional<RpcTransactionReceipt>>() {}.type
}
