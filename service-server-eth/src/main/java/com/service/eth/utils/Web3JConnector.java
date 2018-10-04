package com.service.eth.utils;

import com.service.eth.config.EthConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Component
public class Web3JConnector {

    @Autowired
    private EthConfig ethConfig;

    public Web3j buildWeb3j() {
        Web3j web3j = Web3j.build(new HttpService(ethConfig.getIp()));
        return web3j;
    }
}
