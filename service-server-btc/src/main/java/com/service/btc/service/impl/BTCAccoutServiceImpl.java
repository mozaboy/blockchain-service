package com.service.btc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.service.btc.config.USDTConfig;
import com.service.btc.service.IBTCAccoutService;
import com.service.comm.controller.ResponseResult;
import com.service.comm.model.dto.TransferDTO;
import com.service.comm.model.vo.BTCTransferVO;
import com.service.comm.model.vo.BTCWalletAdressVO;
import com.service.btc.config.CoinNodeRpcMethodConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.service.btc.common.ErrUnify.ERR_BALANCE;
import static com.service.btc.common.JsonKey.ERR;
import static com.service.btc.common.JsonKey.RESULT;
import static com.service.btc.util.JsonUtil.isError;

@Service
public class BTCAccoutServiceImpl extends BTCServiceImpl implements IBTCAccoutService {

    @Autowired
    private CoinNodeRpcMethodConfig coinNodeRpcMethodConfig;
    @Autowired
    private USDTConfig usdtConfig;

    private Logger logger = LoggerFactory.getLogger(BTCAccoutServiceImpl.class);

    @Override
    public ResponseResult<BTCWalletAdressVO> generateWallet() {
        ResponseResult<BTCWalletAdressVO> responseResult = new ResponseResult();
        BTCWalletAdressVO walletAdressVO = new BTCWalletAdressVO();
        String address = super.createWallet(coinNodeRpcMethodConfig.getGetNewAddress(),null);
        walletAdressVO.setAccountAddress(address);
        responseResult.setSucc("btc钱包地址",walletAdressVO);
        return responseResult;
    }

    public BigDecimal getBalance(String strAddress)
    {
        /*if (!checkAddress(strAddress)) {
            logger.error("查询balance BTC地址错误");
            return new BigDecimal(ERR_BALANCE);
        }*/

        JSONObject json = doRequest(coinNodeRpcMethodConfig.getGetBalance(), null);

        if (isError(json)) {
            logger.error("获取BTC余额出错:{}", json.get(ERR));
            return new BigDecimal(ERR_BALANCE);
        }

        return json.getBigDecimal(RESULT);
    }

    @Override
    public BTCTransferVO BTCtransfer(TransferDTO bitcoinTransferDTO) {
        String strAddressFrom = bitcoinTransferDTO.getFromAddress();
        String strAddressTo = bitcoinTransferDTO.getToAddress();
        BigDecimal amount = new BigDecimal(bitcoinTransferDTO.getTransferAmout()).subtract(new BigDecimal(bitcoinTransferDTO.getFee()));
        String hash = super.transfer(strAddressFrom, strAddressTo, amount);
        BTCTransferVO transferVo = new BTCTransferVO();
        transferVo.setTxHash(hash);

        return transferVo;
    }

    @Override
    public BTCTransferVO USDTtransfer(TransferDTO bitcoinTransferDTO) {
        //转账数量扣除fee
        BTCTransferVO ransferVO = new BTCTransferVO();
        BigDecimal transOut = new BigDecimal(bitcoinTransferDTO.getTransferAmout());
        BigDecimal transFee = new BigDecimal(bitcoinTransferDTO.getFee());
        BigDecimal transAmount = transOut.subtract(transFee);
        bitcoinTransferDTO.setTransferAmout(transAmount.toPlainString());

        //转账数量
        String strAmount = bitcoinTransferDTO.getTransferAmout();
        JSONObject jsonTransferResult = doRequest(coinNodeRpcMethodConfig.getOmniSend(),
                bitcoinTransferDTO.getFromAddress(),             //  fromAddress
                bitcoinTransferDTO.getToAddress(),    // toAddress
                usdtConfig.getPropertyId(),
                strAmount);
        if (isError(jsonTransferResult)) {
            logger.error("USDT 转帐给{} value:{}  失败 ：", bitcoinTransferDTO.getToAddress(), strAmount, jsonTransferResult.get(ERR));
            return null;
        }

        logger.info("USDT 转币给{} value:{} 成功", bitcoinTransferDTO.getToAddress(), strAmount);
        String strTxHash = jsonTransferResult.getString(RESULT);
        ransferVO.setTxHash(strTxHash);
        return ransferVO;
    }


}
