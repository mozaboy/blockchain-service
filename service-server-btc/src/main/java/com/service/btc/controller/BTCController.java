package com.service.btc.controller;

import com.service.btc.service.IBTCAccoutService;
import com.service.comm.controller.ResponseResult;
import com.service.comm.model.dto.BTCBalanceDTO;
import com.service.comm.model.dto.TransferDTO;
import com.service.comm.model.vo.BTCTransferVO;
import com.service.comm.model.vo.BTCWalletAdressVO;
import com.service.comm.model.vo.BTCWalletBalanceVO;
import com.service.comm.http.Url;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(Url.SERVICE_SERVER_BTC + Url.API_VERSION)
public class BTCController {

    private Logger log = LoggerFactory.getLogger(BTCController.class);

    @Autowired
    IBTCAccoutService accoutService;

    /**
     * btc钱包地址
     * @param
     * @return
     */
    @PostMapping(Url.BTCWALLETADRESS)
    public ResponseResult<BTCWalletAdressVO> createBtcAccount(){
        log.info("************createBtcAccount start*************");
//
//        ResponseResult<BTCWalletAdressVO> responseResult = new ResponseResult<>();
//        ResponseResult<BTCWalletAdressVO> a= accoutService.generateWallet();
//
//        BTCWalletAdressVO btcWalletAdressVO = new BTCWalletAdressVO();
//        btcWalletAdressVO.setAccountAddress("12AaMuRnzw6vW6s2KPRAGeX53meTf8JbZS");
//
//        responseResult.setSucc("btc钱包地址",btcWalletAdressVO);
        return accoutService.generateWallet();
    }

    /**
     * btc钱包余额
     * @param btcBalanceDTO
     * @return
     */
    @PostMapping(Url.BTCWALLETBALANCE)
    public ResponseResult<BTCWalletBalanceVO> getBalance(@RequestBody BTCBalanceDTO btcBalanceDTO) {
//        log.info("************getBalance start*************");
//
        ResponseResult<BTCWalletBalanceVO> responseResult = new ResponseResult<>();
        BigDecimal balance = accoutService.getBalance(btcBalanceDTO.getBtcAddress());
        BTCWalletBalanceVO walletBalanceVO = new BTCWalletBalanceVO();
        walletBalanceVO.setBalance(balance);
//
//        BTCWalletBalanceVO btcWalletBalanceVO = new BTCWalletBalanceVO();
//
//        btcWalletBalanceVO.setBalance(new BigDecimal("8914.73"));
//
//        responseResult.setSucc("成功获取btc钱包余额",btcWalletBalanceVO);
        responseResult.setSucc("成功获取btc钱包余额",walletBalanceVO);
        return  responseResult;
    }

    /**
     * btc转账
     * @param transferDTO
     * @return
     */
    @PostMapping(Url.BTCTRANSFER)
    public ResponseResult<BTCTransferVO> btcTransfer(@RequestBody TransferDTO transferDTO) {
        log.info("************btcTransfer start*************");

        ResponseResult<BTCTransferVO> responseResult = new ResponseResult<>();

        BTCTransferVO transferVO = accoutService.BTCtransfer(transferDTO);

//        BTCTransferVO btcTransferVO =  new BTCTransferVO();
//        String txHash = "ytfsd754fsdsfdh998fsdf0hjfsd7578sfd";
//        btcTransferVO.setTxHash(txHash);
        responseResult.setSucc("转账中",transferVO);

        return responseResult;
    }

    /**
     * USDT转账
     * @param transferDTO
     * @return
     */
    @PostMapping(Url.USDTTRANSFER)
    public ResponseResult<BTCTransferVO> usdtTransfer(@RequestBody TransferDTO transferDTO) {
        log.info("************usdtTransfer start*************");

        ResponseResult<BTCTransferVO> responseResult = new ResponseResult<>();

        BTCTransferVO transferVO = accoutService.BTCtransfer(transferDTO);

//        BTCTransferVO btcTransferVO =  new BTCTransferVO();
//        String txHash = "ytfsd754fsdsfdh998fsdf0hjfsd7578sfd";
//        btcTransferVO.setTxHash(txHash);
        responseResult.setSucc("转账中",transferVO);

        return responseResult;
    }

    /**
     * btc交易状态查询
     * @param btcBalanceDTO
     * @return
     */
    @PostMapping(Url.QUERYBTCTXSTATUS)
    public ResponseResult<BTCWalletBalanceVO> queryBtcTxStatus(@RequestBody BTCBalanceDTO btcBalanceDTO) {
//        log.info("************queryBtcTxStatus start*************");
//
        ResponseResult<BTCWalletBalanceVO> responseResult = new ResponseResult<>();
        BigDecimal balance = accoutService.getBalance(btcBalanceDTO.getBtcAddress());
        BTCWalletBalanceVO walletBalanceVO = new BTCWalletBalanceVO();
        walletBalanceVO.setBalance(balance);
//
//        BTCWalletBalanceVO btcWalletBalanceVO = new BTCWalletBalanceVO();
//
//        btcWalletBalanceVO.setBalance(new BigDecimal("8914.73"));
//
//        responseResult.setSucc("成功获取btc钱包余额",btcWalletBalanceVO);
        responseResult.setSucc("成功获取btc钱包余额",walletBalanceVO);
        return  responseResult;
    }

    /**
     * 导入BTC钱包
     * @param transferDTO
     * @return
     */
    @PostMapping(Url.IMPORTWALLETADDRESS)
    public ResponseResult<BTCTransferVO> importWalletAddress(@RequestBody TransferDTO transferDTO) {
        log.info("************importWalletAddress start*************");

        ResponseResult<BTCTransferVO> responseResult = new ResponseResult<>();

        BTCTransferVO transferVO = accoutService.USDTtransfer(transferDTO);

//        BTCTransferVO btcTransferVO =  new BTCTransferVO();
//        String txHash = "ytfsd754fsdsfdh998fsdf0hjfsd7578sfd";
//        btcTransferVO.setTxHash(txHash);
        responseResult.setSucc("转账中",transferVO);

        return responseResult;
    }

}
