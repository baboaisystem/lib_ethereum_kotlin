package com.baboaisystem.ethereumkit.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.baboaisystem.ethereumkit.api.jsonrpc.models.RpcTransactionReceipt

@Entity
data class TransactionReceipt(
        @PrimaryKey
        val transactionHash: ByteArray,
        val transactionIndex: Int,
        val blockHash: ByteArray,
        val blockNumber: Long,
        val from: Address,
        val to: Address?,
        val cumulativeGasUsed: Long,
        val gasUsed: Long,
        val contractAddress: Address?,
        val logsBloom: ByteArray,
        val root: ByteArray?,
        val status: Int?
) {
    constructor(rpcTransactionReceipt: RpcTransactionReceipt) : this(
            rpcTransactionReceipt.transactionHash,
            rpcTransactionReceipt.transactionIndex,
            rpcTransactionReceipt.blockHash,
            rpcTransactionReceipt.blockNumber,
            rpcTransactionReceipt.from,
            rpcTransactionReceipt.to,
            rpcTransactionReceipt.cumulativeGasUsed,
            rpcTransactionReceipt.gasUsed,
            rpcTransactionReceipt.contractAddress,
            rpcTransactionReceipt.logsBloom,
            rpcTransactionReceipt.root,
            rpcTransactionReceipt.status
    )

    override fun equals(other: Any?): Boolean {
        if (other !is TransactionReceipt)
            return false

        return transactionHash.contentEquals(other.transactionHash)
    }

    override fun hashCode(): Int {
        return transactionHash.contentHashCode()
    }

}
