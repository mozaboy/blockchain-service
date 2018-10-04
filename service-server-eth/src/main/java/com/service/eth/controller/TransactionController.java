package com.service.eth.controller;

import com.service.comm.controller.ResponseResult;
import com.service.comm.model.dto.*;
import com.service.comm.model.vo.ETHTransferVO;
import com.service.comm.model.vo.TokenTransferVO;
import com.service.eth.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.service.comm.http.Url.*;

/**
 * ETH交易相关controller
 */
@RestController
@RequestMapping(SERVICE_SERVER_ETH + API_VERSION)
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    private Logger log = LoggerFactory.getLogger(TransactionController.class);

    /**
     * 私链币的锁定/解锁
     *
     * @param operateAccountDTO
     * @return
     */
    @PostMapping(OPERATE_ACCOUNT)
    public ResponseResult<Boolean> operateAccount(@RequestBody OperateAccountDTO operateAccountDTO) {
        log.info("************operateAccount start*************");

        ResponseResult<Boolean> responseResult = new ResponseResult<>();
        Boolean operateAccount = true;

        responseResult.setSucc("成功", operateAccount);

        return responseResult;
    }

    /**
     * 公有链ETH转账
     * @param ethTransferDTO
     * @return
     */
    @PostMapping(ETH_PUBLIC_TRANSFER)
    public ResponseResult<ETHTransferVO> ethPublicTransfer(@RequestBody ETHTransferDTO ethTransferDTO) {
        log.info("************ethPublicTransfer start*************");

        ResponseResult<ETHTransferVO> responseResult = new ResponseResult<>();

        ETHTransferVO ethTransferVO = transactionService.getEthTransfer(ethTransferDTO);
        if (ethTransferVO == null || "".equals(ethTransferVO)){
            log.error("转账失败");
            responseResult.setFail("公链ETH转账失败");
            return responseResult;
        }

        responseResult.setSucc("转账中", ethTransferVO);

        return responseResult;
    }


    /**
     * 私有链ETH转账
     * @param ethTransferDTO
     * @return
     */
    @PostMapping(ETH_PRIVATE_TRANSFER)
    public ResponseResult<ETHTransferVO> ethPrivateTransfer(@RequestBody ETHTransferDTO ethTransferDTO) {
        log.info("************ethPrivateTransfer start*************");

        ResponseResult<ETHTransferVO> responseResult = new ResponseResult<>();

        ETHTransferVO ethTransferVO = transactionService.getEthTransfer(ethTransferDTO);
        if (ethTransferVO == null || "".equals(ethTransferVO)){
            log.error("转账失败");
            responseResult.setFail("私链ETH转账失败");
            return responseResult;
        }

        responseResult.setSucc("转账中", ethTransferVO);

        return responseResult;
    }


    /**
     * 私链糖果转账
     * @param candiesTransferDTO
     * @return
     */
    @PostMapping(CANDIES_TRANSFER)
    public ResponseResult<String> candiesTransfer(@RequestBody CandiesTransferDTO candiesTransferDTO) {
        log.info("************candiesTransfer start*************");

        ResponseResult<String> responseResult = new ResponseResult<>();
        String txHash = "fsfs8d787fsdhjk6fskfljro2hrkjwefj";

        responseResult.setSucc("转账中", txHash);
        return responseResult;
    }


    /**
     * 私有链token转账
     * @param tokenTransferDTO
     * @return
     */
    @PostMapping(TOKEN_PRIVATE_TRANSFER)
    public ResponseResult<TokenTransferVO> tokenPrivateTransfer(@RequestBody TokenTransferDTO tokenTransferDTO) {
        log.info("************tokenPrivateTransfer start*************");

        ResponseResult<TokenTransferVO> responseResult = new ResponseResult<>();
        TokenTransferVO tokenTransferVO = transactionService.getTokenTransfer(tokenTransferDTO);

        if (tokenTransferVO == null || "".equals(tokenTransferVO)){
            log.error("私有链token转账失败");
           responseResult.setFail("私有链token转账失败");
           return responseResult;
        }

        responseResult.setSucc("转账中", tokenTransferVO);
        return responseResult;
    }

    /**
     * 公有链token转账
     * @param tokenTransferDTO
     * @return
     */
    @PostMapping(TOKEN_PUBLIC_TRANSFER)
    public ResponseResult<TokenTransferVO> tokenPublicTransfer(@RequestBody TokenTransferDTO tokenTransferDTO) {
        log.info("************tokenPublicTransfer start*************");

        ResponseResult<TokenTransferVO> responseResult = new ResponseResult<>();
        TokenTransferVO tokenTransferVO = transactionService.getTokenTransfer(tokenTransferDTO);

        if (tokenTransferVO == null || "".equals(tokenTransferVO)){
            log.error("私有链token转账");
            responseResult.setFail("私有链token转账失败");
            return responseResult;
        }
        responseResult.setSucc("转账中", tokenTransferVO);
        return responseResult;
    }

    /**
     * 私链中发行token
     * @param releaseTokenDTO
     * @return
     */
    @PostMapping(RELEASE_TOKEN)
    public ResponseResult<Boolean> releaseToken(@RequestBody ReleaseTokenDTO releaseTokenDTO) {
        log.info("************releaseToken start*************");

        ResponseResult<Boolean> responseResult = new ResponseResult<>();

        Boolean falg = true;

        responseResult.setSucc("发币成功", falg);

        return responseResult;
    }
}
