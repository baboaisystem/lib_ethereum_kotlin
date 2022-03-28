package com.baboaisystem.ethereumkit.spv.models

import com.baboaisystem.ethereumkit.core.toHexString

class Signature(val v: Byte,
                val r: ByteArray,
                val s: ByteArray) {
    override fun toString(): String {
        return "Signature [v: $v; r: ${r.toHexString()}; s: ${s.toHexString()}]"
    }
}
