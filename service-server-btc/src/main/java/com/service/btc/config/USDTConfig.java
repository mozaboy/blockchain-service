/*
 * module: service-business-wallet-omni
 * file: USDTConfig.java
 * date: 18-6-13 上午11:11
 */

package com.service.btc.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * Created by Eric on 2018/6/13.
 */
@Component
@Data
public class USDTConfig
{
    @Value("${coinRpcMethod.usdtConfigPropertyId}")
    private int propertyId;
}
