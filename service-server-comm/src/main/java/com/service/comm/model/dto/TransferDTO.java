package com.service.comm.model.dto;

import lombok.Data;

@Data
public class TransferDTO
{
    private String fromAddress;     //转出地址
    private String toAddress;       //转入地址
    private String transferAmout;   //转账金额
    private String fee;             //手续费

}
