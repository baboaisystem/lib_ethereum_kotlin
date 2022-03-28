package com.baboaisystem.ethereumkit.core

import com.baboaisystem.ethereumkit.crypto.CryptoUtils
import com.baboaisystem.ethereumkit.models.Address
import com.baboaisystem.ethereumkit.models.Transaction
import com.baboaisystem.ethereumkit.spv.core.toBigInteger
import com.baboaisystem.ethereumkit.spv.models.RawTransaction
import com.baboaisystem.ethereumkit.spv.models.Signature
import com.baboaisystem.ethereumkit.spv.rlp.RLP
import java.math.BigInteger

class TransactionBuilder(private val address: Address) {

    fun rawTransaction(gasPrice: Long, gasLimit: Long, to: Address, value: BigInteger, nonce: Long, transactionInput: ByteArray = ByteArray(0)): RawTransaction {
        return RawTransaction(gasPrice, gasLimit, to, value, nonce, transactionInput)
    }

    fun transaction(rawTransaction: RawTransaction, signature: Signature): Transaction {
        val transactionHash = CryptoUtils.sha3(encode(rawTransaction, signature))

        return Transaction(
                hash = transactionHash,
                nonce = rawTransaction.nonce,
                input = rawTransaction.data,
                from = address,
                to = rawTransaction.to,
                value = rawTransaction.value,
                gasLimit = rawTransaction.gasLimit,
                gasPrice = rawTransaction.gasPrice,
                timestamp = System.currentTimeMillis() / 1000
        )
    }

    fun encode(rawTransaction: RawTransaction, signature: Signature): ByteArray {
        return RLP.encodeList(
                RLP.encodeLong(rawTransaction.nonce),
                RLP.encodeLong(rawTransaction.gasPrice),
                RLP.encodeLong(rawTransaction.gasLimit),
                RLP.encodeElement(rawTransaction.to.raw),
                RLP.encodeBigInteger(rawTransaction.value),
                RLP.encodeElement(rawTransaction.data),
                RLP.encodeByte(signature.v),
                RLP.encodeBigInteger(signature.r.toBigInteger()),
                RLP.encodeBigInteger(signature.s.toBigInteger()))
    }
}
