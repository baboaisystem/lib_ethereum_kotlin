package com.baboaisystem.erc20kit.core

import com.baboaisystem.erc20kit.decorations.ApproveEventDecoration
import com.baboaisystem.erc20kit.decorations.TransferEventDecoration
import com.baboaisystem.ethereumkit.core.hexStringToByteArrayOrNull
import com.baboaisystem.ethereumkit.core.toRawHexString
import com.baboaisystem.ethereumkit.decorations.ContractEventDecoration
import com.baboaisystem.ethereumkit.models.Address
import com.baboaisystem.ethereumkit.models.TransactionLog
import java.math.BigInteger

fun TransactionLog.getErc20Event(): ContractEventDecoration? {
    return try {
        if (topics.size != 3) {
            return null
        }

        val signature = topics[0].hexStringToByteArrayOrNull()

        val firstParam = Address(topics[1])
        val secondParam = Address(topics[2])

        when {
            signature.contentEquals(TransferEventDecoration.signature) ->
                TransferEventDecoration(address, firstParam, secondParam, BigInteger(data.toRawHexString(), 16))
            signature.contentEquals(ApproveEventDecoration.signature) ->
                ApproveEventDecoration(address, firstParam, secondParam, BigInteger(data.toRawHexString(), 16))
            else ->
                null
        }
    } catch (error: Throwable) {
        error.printStackTrace()
        null
    }
}
