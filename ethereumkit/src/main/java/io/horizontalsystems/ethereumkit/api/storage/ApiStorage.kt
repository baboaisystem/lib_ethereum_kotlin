package com.baboaisystem.ethereumkit.api.storage

import com.baboaisystem.ethereumkit.api.models.AccountState
import com.baboaisystem.ethereumkit.api.models.LastBlockHeight
import com.baboaisystem.ethereumkit.core.IApiStorage

class ApiStorage(
        private val database: ApiDatabase
) : IApiStorage {

    override fun getLastBlockHeight(): Long? {
        return database.lastBlockHeightDao().getLastBlockHeight()?.height
    }

    override fun saveLastBlockHeight(lastBlockHeight: Long) {
        database.lastBlockHeightDao().insert(LastBlockHeight(lastBlockHeight))
    }

    override fun saveAccountState(state: AccountState) {
        database.balanceDao().insert(state)
    }

    override fun getAccountState(): AccountState? {
        return database.balanceDao().getAccountState()
    }

}
