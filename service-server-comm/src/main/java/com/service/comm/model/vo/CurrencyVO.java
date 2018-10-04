package com.service.comm.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyVO {
    private Integer id;
    private String name;
    private Integer logo;
    private String mark;
    private BigDecimal balance;
}
