package com.service.comm.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ReleaseTokenDTO
{

    private String      tokenName;         //名称
    private String      tokenAmount;       //数量
    private BigDecimal  precision;         //小数精度
    private Date        lockStartTime;     //锁仓启始时间
    private Date        lockEndTime;       //锁仓结束时间
    private String      lockAmount;        //锁仓数量
    private String      ownerAddress;      //所有者钱包地址
}
