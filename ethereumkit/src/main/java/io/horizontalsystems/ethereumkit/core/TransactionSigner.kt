package com.baboaisystem.ethereumkit.core

import com.baboaisystem.ethereumkit.crypto.CryptoUtils
import com.baboaisystem.ethereumkit.models.GasPrice
import com.baboaisystem.ethereumkit.models.RawTransaction
import com.baboaisystem.ethereumkit.models.Signature
import com.baboaisystem.ethereumkit.spv.rlp.RLP
import java.math.BigInteger

class TransactionSigner(
        private val privateKey: BigInteger,
        private val chainId: Int
) {

    fun signatureLegacy(rawTransaction: RawTransaction): Signature {
        return when (val gasPrice = rawTransaction.gasPrice) {
            is GasPrice.Eip1559 -> {
                val signatureData = signEip1559(rawTransaction, gasPrice.maxFeePerGas, gasPrice.maxPriorityFeePerGas)
                signatureEip1559(signatureData)
            }
            is GasPrice.Legacy -> {
                val signatureData = signEip155(rawTransaction, gasPrice.legacyGasPrice)
                signatureLegacy(signatureData)
            }
        }
    }

    private fun signatureLegacy(signatureData: ByteArray): Signature {
        return Signature(
                v = (signatureData[64] + if (chainId == 0) 27 else (35 + 2 * chainId)).toByte(),
                r = signatureData.copyOfRange(0, 32),
                s = signatureData.copyOfRange(32, 64)
        )
    }

    private fun signatureEip1559(signatureData: ByteArray): Signature {
        return Signature(
                v = signatureData[64],
                r = signatureData.copyOfRange(0, 32),
                s = signatureData.copyOfRange(32, 64)
        )
    }

    private fun signEip155(rawTransaction: RawTransaction, legacyGasPrice: Long): ByteArray {
        val encodedTransaction = RLP.encodeList(
                RLP.encodeLong(rawTransaction.nonce),
                RLP.encodeLong(legacyGasPrice),
                RLP.encodeLong(rawTransaction.gasLimit),
                RLP.encodeElement(rawTransaction.to.raw),
                RLP.encodeBigInteger(rawTransaction.value),
                RLP.encodeElement(rawTransaction.data),
                RLP.encodeByte(chainId.toByte()),
                RLP.encodeElement(ByteArray(0)),
                RLP.encodeElement(ByteArray(0)))

        val rawTransactionHash = CryptoUtils.sha3(encodedTransaction)

        return CryptoUtils.ellipticSign(rawTransactionHash, privateKey)
    }

    private fun signEip1559(rawTransaction: RawTransaction, maxFeePerGas: Long, maxPriorityFeePerGas: Long): ByteArray {
        val encodedTransaction = RLP.encodeList(
                RLP.encodeInt(chainId),
                RLP.encodeLong(rawTransaction.nonce),
                RLP.encodeLong(maxPriorityFeePerGas),
                RLP.encodeLong(maxFeePerGas),
                RLP.encodeLong(rawTransaction.gasLimit),
                RLP.encodeElement(rawTransaction.to.raw),
                RLP.encodeBigInteger(rawTransaction.value),
                RLP.encodeElement(rawTransaction.data),
                RLP.encode(arrayOf<Any>())
        )
        val rawTransactionHash = CryptoUtils.sha3("0x02".hexStringToByteArray() + encodedTransaction)

        return CryptoUtils.ellipticSign(rawTransactionHash, privateKey)
    }

}