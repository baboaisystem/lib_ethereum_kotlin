#compdef in3

local -a subcmds args sig_in3 sig_erc20 sig_ms
subcmds=(
    'eth_blockNumber:last block number' 
    'eth_getBlockByNumber: get block data <block> <true|false>'
    'eth_getTransactionByHash: get the transaction <hash>'
    'eth_getTransactionByBlockHashAndIndex: get the transaction <blockhash> <index>'
    'eth_getTransactionByBlockNumberAndIndex: get the transaction <block> <index>'
    'eth_getTransactionReceipt: get the transaction receipt <txhash>'
    'eth_getLogs: get the events <filter-object>'
    'eth_getBlockByHash: get block data <blockhash> <true|false>'
    'eth_getCode: get code of a contract <address> <block>'
    'eth_getBalance: get balance of address <address> <block>'
    'web3_clientVersion: returns the client version'
    'web3_sha3: hashes the gives data <data>'
    'net_version: eth version'
    'eth_protocolVersion: RPC-Spec Version'
    'eth_gasPrice: average gas price used in the last blocks'
    'eth_getStorageAt: storage value of an contract <address> <key> <block>'
    'eth_getTransactionCount: nonce of the account <address> <block>'
    'eth_getBlockTransactionCountByHash: Number of Transaction in the Block <blockhash>'
    'eth_getBlockTransactionCountByNumber: Number of Transaction in the Block <blocknumber>'
    'eth_sendTransaction: sends a transaction <transaction-object>'
    'eth_sendRawTransaction: sends a signed transaction <rawdata>'
    'eth_call: calls a function of a contract <transaction-object> <block>'
    'eth_estimateGas: estimates the gas for a call a contract <transaction-object> <block>'
    'in3_nodeList: shows the nodeList'
    'in3_weights: shows the weights of nodeList'
    'in3_cacheClear: clears the cache'
    'in3_sign: requests a node to sign. <blocknumber>'
    'in3_ens: resolve ens-name. <domain> <type> <registryaddress>'
    'ipfs_get: requests and verifies the content for a given ipfs-hash and write the content to stdout <ipfshash>'
    'ipfs_put: reads data from stdin and pushes to the ipfs-network. it write the ipfs-hash to stdout.'
    'send: sends a transaction <signature> ...args'
    'call: calls a contract <signature> ...args'
    'abi_encode: encodes the arguments as described in the method signature using ABI-Encoding. <signature> ...args'
    'abi_decode: decodes the data based on the signature.. <signature> <data>'
    'pk2address: extracts the public address from a private key <pkdata>'
    'pk2public: extracts the public key from a private key <pkdata>'
    'ecrecover: extracts the address and public key from a signature <msg> <signature>'
    'key: reads the private key from JSON-Keystore file and returns the private key. <keyfile>'
    'createkey: generates a random key'
    )

args=(
 '-c[chain]:chain id:(mainnet goerli kovan local)'
 '-st[the type of the signature data]:st:(eth_sign raw hash)'
 '-p[the Verification level]:p:(none standard full)'
 '-pwd[password to unlock the key]:pwd:()'
 '-np[short for -p none]'
 '-ns[short for no stats, which does count this request in the public stats]'
 '-eth[onverts the result (as wei) to ether]'
 '-l[replaces "latest" with latest BlockNumber - the number of blocks given]:latest:(1 2 3 4 5 6 7 8 9 10)'
 '-s[number of signatures to use when verifying]:sigs:(1 2 3 4 5 6 7 8 9 10)'
 '-port[if specified it will run as http-server listening to the given port]:port:(8545)'
 '-b[the blocknumber to use when making calls]:b:(latest earliest 0x)'
 '-to[the target address of the call or send]:to:(0x)'
 '-d[the data for a transaction. This can be a filepath, a 0x-hexvalue or - for stdin]:date:_file'
 '-gas[the gas limit to use when sending transactions]:gas:(21000 100000 250000 500000 1000000 2000000)'
 '-pk[the private key as raw or path to the keystorefile]:pk:_file'
 '-help[displays this help message]'
 '-version[displays the version]'
 '-debug[if given incubed will output debug information when executing]'
 '-value[the value to send when sending a transaction. (hexvalue or float/integer with the suffix eth]:value:(1.0eth)'
 '-w[instead returning the transaction, it will wait until the transaction is mined and return the transactionreceipt]'
 '-md[specifies the minimum Deposit of a node in order to be selected as a signer]'
 '-json[the result will be returned as json]'
 '-hex[the result will be returned as hex]'
 '-kin3[the response including in3-section is returned]'
 '-q[quiet no debug output]'
 '-ri[read response from stdin]'
 '-ro[write raw response to stdout]'
 '-a[max number of attempts before giving up (default 5)]:attempts:(1 2 3 4 5 6 7 8 9)'
 '-rc[number of request per try (default 1)]:requestCount:(1 2 3 4 5 6 7 8 9)'

 ':method:{_describe command subcmds}'
 ':arg1:{_describe command sig_in3 -- sig_erc20 -- sig_ms}'
)

sig_in3=(
    'minDeposi()\:uint: minimal deposit'
    'adminKey()\:address: admin key'
    'nodeRegistryData()\:address:addres of the data contract'
    'supportedToken()\:address: supported token'
    'totalNodes()\:uint: number of nodes'
    'blockRegistry()\:address: BlockHashRegistry'
    'nodes(uint256)\:(string,uint256,uint64,uint192,uint64,address,bytes32): node data'
    'unregisteringNode(address):unregister a node'
    'updateNode(address,string,uint192,uint64,uint256): update nod properties'
    'transferOwnership(address,address): transfers ownership from signer to new owner',
    'registerNode(string,uint192,uint64,uint256): registers a Node'
    'snapshot(): creates a snapshot for the current block'
)
sig_erc20=(
    'balanceOf(address)\:uint:getting the balance of' 
    'name()\:string:token name' 
    'totalSupply()\:uint:total Balance'
    'transfer(address,uint256):transfer tokens'
    'transferFrom(address,address,uint256):transfer from <my> to <your> account <value> tokens'
    'approve(address,uint256):approve the amount for the given address'
    'allowance(address,address)\:uint: the approved amount'
)
sig_ms=(
    'getOwners()\:(address[]): multisig'
    'getMessageHash(bytes)\:bytes: gets the message hash of a transaction'
    'isOwner(address)\:bool:is owner'
    'signedMessages(bytes32)\:uint: number of signed messages'
    'approvedHashes(address,bytes32)\:uint:check if the hash was approved'
    'nonce()\:uint:the nonce of the multisig'
    'getModules()\:address[]:List of modules'
    'getTransactionHash(address,uint256,bytes,uint8,uint256,uint256,uint256,address,address,uint256)\:bytes32:calculates the transaction hash'
    'getThreshold()\:uint'
    'addOwnerWithThreshold(address,uint256):adds an owner with the given threshold'
    'changeThreshold(uint256): changes the threshold'
    'execTransaction(address,uint256,bytes,uint8,uint256,uint256,uint256,address,address,bytes): executes a transaction'
)

_arguments -C $args
