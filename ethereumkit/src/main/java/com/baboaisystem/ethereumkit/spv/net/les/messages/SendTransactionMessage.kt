package com.baboaisystem.ethereumkit.spv.net.les.messages

import com.baboaisystem.ethereumkit.core.toHexString
import com.baboaisystem.ethereumkit.spv.models.RawTransaction
import com.baboaisystem.ethereumkit.spv.models.Signature
import com.baboaisystem.ethereumkit.spv.net.IOutMessage
import com.baboaisystem.ethereumkit.spv.rlp.RLP
import java.math.BigInteger

class SendTransactionMessage(val requestID: Long, val rawTransaction: RawTransaction, val signature: Signature) : IOutMessage {

    override fun encoded(): ByteArray {
        return RLP.encodeList(
                RLP.encodeBigInteger(BigInteger.valueOf(requestID)),
                RLP.encodeList(
                        RLP.encodeList(
                                RLP.encodeLong(rawTransaction.nonce),
                                RLP.encodeLong(rawTransaction.gasPrice),
                                RLP.encodeLong(rawTransaction.gasLimit),
                                RLP.encodeElement(rawTransaction.to.raw),
                                RLP.encodeBigInteger(rawTransaction.value),
                                RLP.encodeElement(rawTransaction.data),
                                RLP.encodeByte(signature.v),
                                RLP.encodeElement(signature.r),
                                RLP.encodeElement(signature.s)
                        )
                )
        )
    }

    override fun toString(): String {
        return "SendTransaction [requestId: $requestID; nonce: ${rawTransaction.nonce}; gasPrice: ${rawTransaction.gasPrice}; gasLimit: ${rawTransaction.gasLimit}; to: ${rawTransaction.to}; " +
                "value: ${rawTransaction.value}; data: ${rawTransaction.data}; " +
                "v: ${signature.v}; r: ${signature.r.toHexString()}; s: ${signature.s.toHexString()}]"
    }

}
