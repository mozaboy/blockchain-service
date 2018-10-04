package com.service.comm.model.dto;

import lombok.Data;

@Data
public class OperateAccountDTO
{
    private String tokenAddress;            //代币账户地址
    private String lockFlag;                //加锁、解锁标志
    private String lockTime;                //锁仓时间
}
