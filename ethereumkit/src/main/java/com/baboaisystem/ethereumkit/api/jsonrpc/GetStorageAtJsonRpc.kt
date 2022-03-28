package com.baboaisystem.ethereumkit.api.jsonrpc

import com.baboaisystem.ethereumkit.models.Address
import com.baboaisystem.ethereumkit.models.DefaultBlockParameter

class GetStorageAtJsonRpc(
        @Transient val contractAddress: Address,
        @Transient val position: ByteArray,
        @Transient val defaultBlockParameter: DefaultBlockParameter
) : DataJsonRpc(
        method = "eth_getStorageAt",
        params = listOf(contractAddress, position, defaultBlockParameter)
)
