package com.baboaisystem.ethereumkit.spv.net.devp2p.messages

import com.baboaisystem.ethereumkit.core.hexStringToByteArray
import com.baboaisystem.ethereumkit.spv.net.IInMessage
import com.baboaisystem.ethereumkit.spv.net.IOutMessage

class PongMessage() : IInMessage, IOutMessage {

    constructor(payload: ByteArray) : this()

    override fun encoded(): ByteArray {
        return payload
    }

    override fun toString(): String {
        return "Pong"
    }

    companion object {
        val payload = "C0".hexStringToByteArray()
    }
}
