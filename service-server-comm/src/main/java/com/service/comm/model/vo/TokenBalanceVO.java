package com.service.comm.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenBalanceVO
{
    private String      tokenTotal;             //Token总供应量
    private String      ownerAddressAmount;     //持有人地址数量
    private BigDecimal  lockPrice;              //锁仓金额
    private Date        unlockTime;             //解锁时间
}
