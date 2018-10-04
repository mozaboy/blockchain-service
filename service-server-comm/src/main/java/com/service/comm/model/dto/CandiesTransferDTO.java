package com.service.comm.model.dto;

import lombok.Data;

@Data
public class CandiesTransferDTO
{
    private String walletAddressList;       //钱包地址列表
    private String amountList;              //数量列表
    private String GasLimit;                //矿工限制
}
