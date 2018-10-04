/**
 * @author juhongtao
 * @create 2018-08-13 10:42
 * @desc config node info
 **/
package com.service.btc.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class CoinNodeInfoConfig {
    @Value("${coinNodeInfo.coinNodeServer}")
    private String coinNodeServer;
    @Value("${coinNodeInfo.coinNodeUserName}")
    private String coinNodeUserName;
    @Value("${coinNodeInfo.coinNodePassword}")
    private String coinNodePassword;
    @Value("${coinNodeInfo.coinNodePropertyId}")
    private String coinNodePropertyId;
}

