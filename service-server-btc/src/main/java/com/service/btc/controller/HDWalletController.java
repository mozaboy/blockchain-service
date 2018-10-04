package com.service.btc.controller;

import com.service.btc.service.IBTCAccoutService;
import com.service.comm.controller.ResponseResult;
import com.service.comm.model.dto.TransferDTO;
import com.service.comm.model.vo.BTCTransferVO;
import com.service.comm.model.vo.BTCWalletAdressVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.service.comm.http.Url.*;


@RestController
@RequestMapping(SERVICE_SERVER_BTC + API_VERSION)
public class HDWalletController {

    private Logger log = LoggerFactory.getLogger(HDWalletController.class);

    @Autowired
    IBTCAccoutService accoutService;

    /**
     * 生成HD钱包地址
     * @param
     * @return
     */
    @PostMapping(CREATEHDACCOUNT)
    public ResponseResult<BTCWalletAdressVO> createHDAccount(){
        log.info("************createHDAccount start*************");
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
     * 导入HD钱包
     * @param transferDTO
     * @return
     */
    @PostMapping(IMPORTHDWALLETADDRESS)
    public ResponseResult<BTCTransferVO> importHDWalletAddress(@RequestBody TransferDTO transferDTO) {
        log.info("************importHDWalletAddress start*************");

        ResponseResult<BTCTransferVO> responseResult = new ResponseResult<>();

        BTCTransferVO transferVO = accoutService.USDTtransfer(transferDTO);

//        BTCTransferVO btcTransferVO =  new BTCTransferVO();
//        String txHash = "ytfsd754fsdsfdh998fsdf0hjfsd7578sfd";
//        btcTransferVO.setTxHash(txHash);
        responseResult.setSucc("转账中",transferVO);

        return responseResult;
    }

}
