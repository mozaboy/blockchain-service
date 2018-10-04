package com.service.comm.model.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class QueryWalletBalanceVO
{
    private String      tokenName;      //token名称
    private BigDecimal  balance;        //余额
}
