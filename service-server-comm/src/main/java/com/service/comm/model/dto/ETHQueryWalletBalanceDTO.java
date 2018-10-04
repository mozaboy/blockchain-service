package com.service.comm.model.dto;

import lombok.Data;

@Data
public class ETHQueryWalletBalanceDTO
{
    private String walletAddress;       //钱包地址

    private String tokenName;           //toke名称
}
