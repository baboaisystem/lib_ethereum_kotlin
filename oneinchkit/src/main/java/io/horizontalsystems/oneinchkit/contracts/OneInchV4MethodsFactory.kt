package com.baboaisystem.oneinchkit.contracts

import com.baboaisystem.ethereumkit.contracts.ContractMethod
import com.baboaisystem.ethereumkit.contracts.ContractMethodHelper
import com.baboaisystem.ethereumkit.contracts.ContractMethodsFactory

class OneInchV4MethodsFactory : ContractMethodsFactory {
    override val methodIds: List<ByteArray> = listOf(
            ContractMethodHelper.getMethodId("fillOrderRFQ((uint256,address,address,address,address,uint256,uint256),bytes,uint256,uint256)"),
            ContractMethodHelper.getMethodId("fillOrderRFQTo((uint256,address,address,address,address,uint256,uint256),bytes,uint256,uint256,address)"),
            ContractMethodHelper.getMethodId("fillOrderRFQToWithPermit((uint256,address,address,address,address,uint256,uint256),bytes,uint256,uint256,address,bytes)"),
            ContractMethodHelper.getMethodId("clipperSwap(address,address,uint256,uint256)"),
            ContractMethodHelper.getMethodId("clipperSwapTo(address,address,address,uint256,uint256)"),
            ContractMethodHelper.getMethodId("clipperSwapToWithPermit(address,address,address,uint256,uint256,bytes)"),
            ContractMethodHelper.getMethodId("uniswapV3Swap(uint256,uint256,uint256[])"),
            ContractMethodHelper.getMethodId("uniswapV3SwapTo(address,uint256,uint256,uint256[])"),
            ContractMethodHelper.getMethodId("uniswapV3SwapToWithPermit(address,address,uint256,uint256,uint256[],bytes)"),
            ContractMethodHelper.getMethodId("unoswapWithPermit(address,uint256,uint256,bytes32[],bytes)")
    )

    override fun createMethod(inputArguments: ByteArray): ContractMethod {
        return OneInchV4Method()
    }
}