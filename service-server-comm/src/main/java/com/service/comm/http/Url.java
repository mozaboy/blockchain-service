package com.service.comm.http;

/**
 * http请求地址
 */
public class Url {

    public static final String
        SERVICE_SERVER_ETH              = "/service-server-eth",
        SERVICE_SERVER_BTC              = "/service-server-btc",
        SERVICE_SERVER_FABRIC           = "/service-server-fabric";

    public static final String
        API_VERSION                     = "/v1";

    public static final String
        COINTYPE                        = "/{coinType}",

        TOKENADDRESS                    = "/{tokenAddress}",
        TRACEHASH                       = "/{traceHash}";



    public static final String

        BTCTRANSFER                     = "/btcTransfer",                               // btc转账/收款
        USDTTRANSFER                    = "/usdtTransfer",                              // USDT转账/收款
        IMPORTWALLETADDRESS             = "/importWalletAddress",                       // 导入btc钱包
        BTCWALLETBALANCE                = "/getBalance",                                // btc钱包余额

        QUERYBTCTXSTATUS                = "/queryBtcTxStatus",                          // btc交易状态查询
        BTCWALLETADRESS                 ="/createBtcAccount",                           // btc钱包地址
        CREATEHDACCOUNT                 ="/createHDAccount",                           // HD钱包地址
        IMPORTHDWALLETADDRESS           ="/importHDWalletAddress",                     // 导入HD钱包

        CREATEFABRICACCOUNT             ="/createFabricAccount",                       //联盟链生成Fabric账号
        CERTIFICATETRACE                ="/certificateTrace",                          //联盟链资产报告溯源
        ASSETINFOTRACE                  ="/assetInfoTrace",                            //联盟链资产信息溯源
        INFOCOCHAIN                     ="/infoCochain",                               //联盟链资产信息上链
        FILECOCHAIN                     ="/fileCochain",                               //联盟链资产报告上链

        DEFAULTTRANSFER                 = "/defaultTransfer",                           // 用户提币/平台转账
        GETADRESS                       = "/getaddress",
        NEWTRANSACTIONFILTER            = "/newTransactionFilter",                      // 当前交易监听
        MONITORBLOCK                    = "/monitorBLock",                              // 当前区块监听
        MONITORHISTORYBLOCK             = "/monitorHistoryBLock",                       // 历史区块监听
        GETBALANCE                      = "/getBalance",                                // 获取余额
        GETBALANCEINFO                  = "/getBalanceInfo";                            // 获取余额信息

    /**
     * QueryController
     */
    public static final String

        QUERY_ERCTX_GAS                 = "/queryErcTxGas",                             // 查询ETH公链和ETH私链交易的gas

        QUERY_ERCTX_STATUS              = "/queryErcTxStatus",                          // 查询ETH及token交易状态

        QUERY_PUBLIC_TOKEN_BALANCE      = "/queryPublicTokenBalance",                   // ETH公链Token余额查询
        QUERY_PRIVATE_TOKEN_BALANCE     = "/queryPrivateTokenBalance",                  // ETH私链Token余额查询

        QUERY_PRIVATE_WALLET_BALANCE    = "/queryPrivateWalletBalance",                 // ETH私链钱包余额查询
        QUERY_PUBLIC_WALLET_BALANCE     = "/queryPublicWalletBalance";                  // ETH公链钱包余额查询

    /**
     *  AddressController
     */
    public static final String

        CREATE_PRIVATE_ADDRESS          = "/createPrivateAddress",                      // ETH私链钱包地址

        CREATE_PUBLIC_ACCOUNT           = "/createPublicAccount",                       // ETH公链钱包地址

        CRETAE_CANDY_ACCOUNT            = "/cretaeCandyAccount",                        // 创建糖果钱包

        IMPORT_PRIVATE_WALLET_ADDRESS   = "/importPrivateWalletAddress",                // 导入ETH私链钱包地址
        IMPORT_PUBLIC_WALLET_ADDRESS    = "/importPublicWalletAddress";                 // 导入ETH公链钱包地址


    /**
     *  TransactionController
     */
    public static final String

        ETH_PUBLIC_TRANSFER              = "/ethPublicTransfer",                         // 公有链ETH转账
        ETH_PRIVATE_TRANSFER             = "/ethPrivateTransfer",                        // 私有链ETH转账

        TOKEN_PUBLIC_TRANSFER            = "/tokenPublicTransfer",                       // 公有链token转账
        TOKEN_PRIVATE_TRANSFER           = "/tokenPrivateTransfer",                      // 私有链token转账

        CANDIES_TRANSFER                 = "/candiesTransfer",                           // 私链糖果转账
        OPERATE_ACCOUNT                  = "/operateAccount",                            // 私链币的锁定/解锁
        RELEASE_TOKEN                    = "/releaseToken";                              // 私链中发行token

}

