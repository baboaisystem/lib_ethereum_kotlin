package com.baboaisystem.ethereumkit.api.jsonrpc

import com.baboaisystem.ethereumkit.models.Address
import com.baboaisystem.ethereumkit.models.DefaultBlockParameter

class GetTransactionCountJsonRpc(
        @Transient val address: Address,
        @Transient val defaultBlockParameter: DefaultBlockParameter
) : LongJsonRpc(
        method = "eth_getTransactionCount",
        params = listOf(address, defaultBlockParameter)
)
