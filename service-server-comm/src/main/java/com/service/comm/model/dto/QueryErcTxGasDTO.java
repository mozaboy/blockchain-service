package com.service.comm.model.dto;

import lombok.Data;

@Data
public class QueryErcTxGasDTO
{
    private String txHash;          //交易hash
    private String flag;            //公链还是私链标志位
}
