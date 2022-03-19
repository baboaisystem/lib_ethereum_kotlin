package com.baboaisystem.ethereumkit.spv.net.les

import com.baboaisystem.ethereumkit.spv.core.toLong
import com.baboaisystem.ethereumkit.spv.rlp.RLPList

class MaxCost(rlpList: RLPList) {

    val messageCode: Long = rlpList[0].toLong()
    val baseCost: Long = rlpList[1].toLong()
    val requestCost: Long = rlpList[2].toLong()

    override fun toString(): String {
        return "MaxCost [messageCode: ${String.format("0x%02x", messageCode)}; baseCost: ${String.format("%,d", baseCost)}; requestCost:  ${String.format("%,d", requestCost)} ]"
    }
}
