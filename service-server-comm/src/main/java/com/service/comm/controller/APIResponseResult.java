package com.service.comm.controller;

import lombok.Data;

@Data
public class APIResponseResult<T> {
    private Long date;
    private T ticker;
}
