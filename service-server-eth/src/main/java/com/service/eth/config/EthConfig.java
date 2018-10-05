package com.service.eth.service.eth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;

@Component
@Data
@ConfigurationProperties(prefix = "geth")
public class EthConfig {

    private String ip;                          //节点ip地址
    private String newWalletPath;               //钱包文件存放地址
    private BigInteger gasPrice;                //gas价格
    private BigInteger gasLimit;                //gas限制
    private BigDecimal fee;                     //转账手续费 单位：eth
    private BigDecimal collFee;                 //转账手续费 单位：eth
}
