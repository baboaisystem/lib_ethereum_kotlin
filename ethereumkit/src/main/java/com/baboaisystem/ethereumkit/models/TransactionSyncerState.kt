package com.baboaisystem.ethereumkit.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TransactionSyncerState(
        @PrimaryKey
        val id: String,
        val lastBlockNumber: Long
)
