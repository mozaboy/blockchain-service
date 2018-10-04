/**
 * @author juhongtao
 * @create 2018-08-13 10:46
 * @desc node rpc method config
 **/
package com.service.btc.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class CoinNodeRpcMethodConfig {
    @Value("${coinRpcMethod.dumpPrivateKey}")
    private String dumpPrivateKey;
    @Value("${coinRpcMethod.getBalance}")
    private String getBalance;
    @Value("${coinRpcMethod.getBlockCount}")
    private String getBlockCount;
    @Value("${coinRpcMethod.getOmniBalance}")
    private String getOmniBalance;
    @Value("${coinRpcMethod.getBlockInfo}")
    private String getBlockInfo;
    @Value("${coinRpcMethod.getBlock}")
    private String getBlock;
    @Value("${coinRpcMethod.getBlockHash}")
    private String getBlockHash;
    @Value("${coinRpcMethod.getNewAddress}")
    private String getNewAddress;
    @Value("${coinRpcMethod.getNodeInfo}")
    private String getNodeInfo;
    @Value("${coinRpcMethod.getTransaction}")
    private String getTransaction;
    @Value("${coinRpcMethod.getOmniTransaction}")
    private String getOmniTransaction;
    @Value("${coinRpcMethod.getWalletInfo}")
    private String getWalletInfo;
    @Value("${coinRpcMethod.listBlockTransactions}")
    private String listBlockTransactions;
    @Value("${coinRpcMethod.listAddressGroupings}")
    private String listAddressGroupings;
    @Value("${coinRpcMethod.minConfirmation}")
    private String minConfirmation;
    @Value("${coinRpcMethod.sendToAddress}")
    private String sendToAddress;
    @Value("${coinRpcMethod.omniSend}")
    private String omniSend;
    @Value("${coinRpcMethod.validateAddress}")
    private String validateAddress;
}

