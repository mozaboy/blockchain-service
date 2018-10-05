package com.service.btc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.service.btc.common.JsonKey;
import com.service.btc.config.CoinNodeRpcMethodConfig;
import com.service.btc.util.HttpUtil;
import com.service.btc.util.JsonUtil;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import com.service.btc.config.CoinNodeInfoConfig;

import static com.service.btc.common.ErrUnify.ERR_TRANSFER_VALUE_INVALID;
import static com.service.btc.common.HttpKey.*;
import static com.service.btc.common.JsonKey.*;
import static com.service.btc.common.HttpValue.PARAM_HEADER_AUTHORIZATION_BASIC;
import static com.service.btc.common.HttpValue.PARAM_JSON_RPC_VERSION;

public class BTCServiceImpl {

    @Autowired
    private CoinNodeInfoConfig coinNodeInfoConfig;
    @Autowired
    private CoinNodeRpcMethodConfig coinNodeRpcMethodConfig;

    private Logger logger = LoggerFactory.getLogger(BTCServiceImpl.class);

    /**
     * 创建钱包地址
     *
     * @return
     */
    public String createWallet(String strMethod,String userId)
    {
        JSONObject json = doRequest(strMethod);

        if (JsonUtil.isError(json)) {
            logger.error("创建地址失败:{}", json.get(ERR));
            return "";
        }

        return json.getString(RESULT);
    }

    /**
     * 转账
     * @param strAddressTo
     * @param bigDeAmount
     *
     * @return
     */
    public String transfer(String strAddressFrom, String strAddressTo, BigDecimal bigDeAmount)
    {
        // TODO check enumCoin

//        if (!checkAddress(strAddressFrom) || !checkAddress(strAddressTo)) {
//            logger.error(ERR_ADDRESS_INVALID);
//            return "";
//        }

        if (bigDeAmount.compareTo(new BigDecimal(0)) < 0) {
            logger.error(ERR_TRANSFER_VALUE_INVALID);
            return "";
        }

        JSONObject json = new JSONObject();
        double dAmount = bigDeAmount.doubleValue();

        json = doRequest(coinNodeRpcMethodConfig.getSendToAddress(), strAddressTo, dAmount);


        if (JsonUtil.isError(json)) {
            logger.error("转帐给{} value:{}  失败 ：", strAddressTo, dAmount, json.get(JsonKey.ERR));
            return "";
        }

        logger.info("转币给{} value:{} 成功", strAddressTo, dAmount);

        return json.getString(RESULT);
    }




    /**
     * http请求
     *
     * @param method
     * @param params
     *
     * @return
     */
    public JSONObject doRequest(String method, Object... params)
    {
        JSONObject param = new JSONObject();

        param.put(PARAM_ID, System.currentTimeMillis() + "");
        param.put(PARAM_JSON_RPC, PARAM_JSON_RPC_VERSION);
        param.put(PARAM_METHOD, method);

        if (params != null) {
            param.put(PRARMS, params);
        }

        String strHttpBase64Info = Base64.encodeBase64String((coinNodeInfoConfig.getCoinNodeUserName() + ":" + coinNodeInfoConfig.getCoinNodePassword()).getBytes());

        Map<String, String> headers = new HashMap<>(2);

        headers.put(HEADER_AUTHORIZATION, PARAM_HEADER_AUTHORIZATION_BASIC + strHttpBase64Info);

        String strText = HttpUtil.jsonPost(coinNodeInfoConfig.getCoinNodeServer(), headers, param.toJSONString());

        return JSON.parseObject(strText);
    }
}
