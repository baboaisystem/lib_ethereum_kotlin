package com.baboaisystem.oneinchkit.decorations

import com.baboaisystem.ethereumkit.decorations.ContractMethodDecoration
import com.baboaisystem.ethereumkit.models.Address

abstract class OneInchMethodDecoration: ContractMethodDecoration() {

    sealed class Token {
        object EvmCoin : Token()
        class Eip20(val address: Address) : Token()

        override fun toString(): String {
            return when(this) {
                EvmCoin -> "EvmCoin"
                is Eip20 -> "Eip20($address)"
            }
        }
    }

}
