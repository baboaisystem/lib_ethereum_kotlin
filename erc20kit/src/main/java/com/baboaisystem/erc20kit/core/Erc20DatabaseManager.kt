package com.baboaisystem.erc20kit.core

import android.content.Context
import com.baboaisystem.erc20kit.core.room.Erc20KitDatabase
import com.baboaisystem.ethereumkit.core.EthereumKit
import com.baboaisystem.ethereumkit.models.Address

internal object Erc20DatabaseManager {

    fun getErc20Database(context: Context, networkType: EthereumKit.NetworkType, walletId: String, contractAddress: Address): Erc20KitDatabase {
        return Erc20KitDatabase.getInstance(context, "${getDbNameBase(networkType, walletId)}-${contractAddress.hex}")
    }

    fun clear(context: Context, networkType: EthereumKit.NetworkType, walletId: String) {
        synchronized(this) {
            val dbNameBase = getDbNameBase(networkType, walletId)

            context.databaseList().forEach {
                if (it.contains(dbNameBase)) {
                    context.deleteDatabase(it)
                }
            }
        }
    }

    private fun getDbNameBase(networkType: EthereumKit.NetworkType, walletId: String): String {
        return "Erc20-${networkType.name}-$walletId"
    }

}
