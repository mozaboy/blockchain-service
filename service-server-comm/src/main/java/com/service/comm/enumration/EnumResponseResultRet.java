/*
 * module: jar-common
 * file: EnumResponseResultRet
 * date: 18-5-12 下午1:59
 */

package com.service.comm.enumration;

public enum EnumResponseResultRet
{
    SUCC(0),
    FAIL(1);

    private int code;

    EnumResponseResultRet(int index) {
        this.code = index;
    }

    public int getCode() {
        return code;
    }
}
