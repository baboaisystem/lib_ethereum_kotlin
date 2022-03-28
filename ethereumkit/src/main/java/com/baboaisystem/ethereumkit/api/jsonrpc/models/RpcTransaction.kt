package com.baboaisystem.ethereumkit.api.jsonrpc.models

import com.google.gson.annotations.SerializedName
import com.baboaisystem.ethereumkit.models.Address
import com.baboaisystem.ethereumkit.models.Transaction
import java.math.BigInteger

class RpcTransaction(
        val hash: ByteArray,
        val nonce: Long,
        val blockHash: ByteArray?,
        val blockNumber: Long?,
        val transactionIndex: Int?,
        val from: Address,
        val to: Address?,
        val value: BigInteger,
        val gasPrice: Long,
        @SerializedName("gas")
        val gasLimit: Long,
        val input: ByteArray
) {
    constructor(transaction: Transaction) : this(
            transaction.hash,
            transaction.nonce,
            null,
            null,
            null,
            transaction.from,
            transaction.to,
            transaction.value,
            transaction.gasPrice,
            transaction.gasLimit,
            transaction.input
    )
}
