package com.baboaisystem.ethereumkit.api.jsonrpc

class GasPriceJsonRpc : LongJsonRpc(
        method = "eth_gasPrice",
        params = listOf()
)
