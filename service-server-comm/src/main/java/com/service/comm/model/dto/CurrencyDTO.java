package com.service.comm.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

@Data
public class CurrencyDTO implements Serializable {
    private String currencyName;
    private String coinType;
    private BigInteger bigInHeight;
    private String currencyAddress;
    private String contractAddress;
    private String currencyDecimal;
}
