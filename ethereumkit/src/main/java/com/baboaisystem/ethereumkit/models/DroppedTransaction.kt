package com.baboaisystem.ethereumkit.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class DroppedTransaction(
        @PrimaryKey
        val hash: ByteArray,
        val replacedWith: ByteArray
)
