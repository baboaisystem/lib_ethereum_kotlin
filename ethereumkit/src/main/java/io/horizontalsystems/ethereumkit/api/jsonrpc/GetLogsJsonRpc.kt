package com.baboaisystem.ethereumkit.api.jsonrpc

import com.google.gson.reflect.TypeToken
import com.baboaisystem.ethereumkit.models.Address
import com.baboaisystem.ethereumkit.models.DefaultBlockParameter
import com.baboaisystem.ethereumkit.models.TransactionLog
import java.lang.reflect.Type

class GetLogsJsonRpc(
        @Transient val address: Address?,
        @Transient val fromBlock: DefaultBlockParameter,
        @Transient val toBlock: DefaultBlockParameter,
        @Transient val topics: List<ByteArray?>
) : JsonRpc<ArrayList<TransactionLog>>(
        method = "eth_getLogs",
        params = listOf(GetLogsParams(address, fromBlock, toBlock, topics))
) {
    @Transient
    override val typeOfResult: Type = object : TypeToken<List<TransactionLog>>() {}.type

    data class GetLogsParams(
            val address: Address?,
            val fromBlock: DefaultBlockParameter,
            val toBlock: DefaultBlockParameter,
            val topics: List<ByteArray?>
    )
}
