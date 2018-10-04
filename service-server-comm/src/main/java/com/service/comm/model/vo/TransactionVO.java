package com.service.comm.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 转账VO
 */
@Data
public class TransactionVO implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String txHash;
    private String orderNo;
    private String toAddress;
    private String fromAddress;
    private BigDecimal amount;
    private int type;

    @Override
    public String toString()
    {
        String strBuffer = "TransactionVO{" +
                "txHash='" + txHash + '\'' +
                ", toAddress='" + toAddress + '\'' +
                ", fromAddress='" + fromAddress + '\'' +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                '}';

        return strBuffer;
    }
}
