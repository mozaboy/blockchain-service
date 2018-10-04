/*
 * module: service-business-wallet-omni
 * file: JsonUtil.java
 * date: 18-6-9 下午3:05
 */

package com.service.btc.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import static com.service.btc.common.ErrUnify.NULL;

import static com.service.btc.common.JsonKey.ERR;


/**
 * @des:    json工具
 * @author:
 * @date:
 * @modify:
 * @date:
 */
public class JsonUtil
{
    /**
     * 交易json是否有错
     * @param json
     * @return
     */
    public static boolean isError(JSONObject json)
    {
        if (json == null || (StringUtils.isNotEmpty(json.getString(ERR)) && json.get(ERR) != NULL)){
            return true;
        }

        return false;
    }
}
