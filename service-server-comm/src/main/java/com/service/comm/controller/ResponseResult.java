package com.service.comm.controller;

import com.service.comm.enumration.EnumResponseResultRet;

public class ResponseResult<T> {
    /**
     * 0 - success
     * 1 - failed
     */
    private Integer ret;
    private String msg;
    private T data;

    public ResponseResult() {
        this.ret = EnumResponseResultRet.SUCC.getCode();
    }

    public ResponseResult(String msg) {
        this.ret = EnumResponseResultRet.SUCC.getCode();
        this.msg = msg;
    }

    public void setFail(String msg) {
        this.ret = EnumResponseResultRet.FAIL.getCode();
        this.msg = msg;
    }

    public void setSucc(String msg) {
        this.ret = EnumResponseResultRet.SUCC.getCode();
        this.msg = msg;
    }

    public void setSucc(String msg, T t) {
        setSucc(msg);
        this.data = t;
    }

    public Integer getret() {
        return ret;
    }

    public void setret(Integer ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}

