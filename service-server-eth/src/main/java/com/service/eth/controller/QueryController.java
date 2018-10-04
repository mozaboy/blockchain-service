package com.service.eth.controller;

import com.service.comm.controller.ResponseResult;
import com.service.comm.model.dto.ETHQueryWalletBalanceDTO;
import com.service.comm.model.dto.QueryErcTxGasDTO;
import com.service.comm.model.dto.QueryErcTxStatusDTO;
import com.service.comm.model.dto.QueryTokenBalanceDTO;
import com.service.comm.model.vo.QueryWalletBalanceVO;
import com.service.comm.model.vo.TokenBalanceVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

import static com.service.comm.http.Url.*;

/**
 * ETH查询（余额，token等）相关controller
 */
@RestController
@RequestMapping(SERVICE_SERVER_ETH + API_VERSION)
public class QueryController {

    private Logger log = LoggerFactory.getLogger(QueryController.class);


    /**
     * ETH私链token余额查询
     * @param queryTokenBalanceDTO
     * @return
     */
    @PostMapping(QUERY_PRIVATE_TOKEN_BALANCE)
    public ResponseResult<TokenBalanceVO> queryPrivateTokenBalance(@RequestBody QueryTokenBalanceDTO queryTokenBalanceDTO) {
        log.info("************queryPrivateTokenBalance start*************");

        ResponseResult<TokenBalanceVO> responseResult = new ResponseResult<>();

        TokenBalanceVO tokenBalanceVO = new TokenBalanceVO();

        tokenBalanceVO.setLockPrice(new BigDecimal("323"));
        tokenBalanceVO.setOwnerAddressAmount("ox234JFHJEINFJFBHD8767hJJK");
        tokenBalanceVO.setTokenTotal("3452");
        tokenBalanceVO.setUnlockTime(new Date());

        responseResult.setSucc("查询成功",tokenBalanceVO);

        return responseResult;
    }

    /**
     * ETH公链token余额查询
     * @param queryTokenBalanceDTO
     * @return
     */
    @PostMapping(QUERY_PUBLIC_TOKEN_BALANCE)
    public ResponseResult<TokenBalanceVO> queryPublicTokenBalance(@RequestBody QueryTokenBalanceDTO queryTokenBalanceDTO) {
        log.info("************queryPublicTokenBalance start*************");

        ResponseResult<TokenBalanceVO> responseResult = new ResponseResult<>();

        TokenBalanceVO tokenBalanceVO = new TokenBalanceVO();
        tokenBalanceVO.setLockPrice(new BigDecimal("323"));
        tokenBalanceVO.setOwnerAddressAmount("ox234JFHJEINFJFBHD8767hJJK");
        tokenBalanceVO.setTokenTotal("3452");
        tokenBalanceVO.setUnlockTime(new Date());

        responseResult.setSucc("查询成功",tokenBalanceVO);

        return responseResult;
    }

    /**
     * ETH私链钱包余额查询
     * @param ethQueryWalletBalanceDTO
     * @return
     */
    @PostMapping(QUERY_PRIVATE_WALLET_BALANCE)
    public ResponseResult<QueryWalletBalanceVO> queryPrivateWalletBalance(@RequestBody ETHQueryWalletBalanceDTO ethQueryWalletBalanceDTO) {
        log.info("************queryPrivateWalletBalance start*************");

        ResponseResult<QueryWalletBalanceVO> responseResult = new ResponseResult<>();
        QueryWalletBalanceVO queryWalletBalanceVO = new QueryWalletBalanceVO();

        queryWalletBalanceVO.setBalance(new BigDecimal("8584337"));
        queryWalletBalanceVO.setTokenName("ETH");

        responseResult.setSucc("查询余额成功",queryWalletBalanceVO);
        return responseResult;
    }

    /**
     * ETH公链钱包余额查询
     * @param ethQueryWalletBalanceDTO
     * @return
     */
    @PostMapping(QUERY_PUBLIC_WALLET_BALANCE)
    public ResponseResult<QueryWalletBalanceVO> queryPublicWalletBalance(@RequestBody ETHQueryWalletBalanceDTO ethQueryWalletBalanceDTO) {
        log.info("************queryPublicWalletBalance start*************");

        ResponseResult<QueryWalletBalanceVO> responseResult = new ResponseResult<>();

        QueryWalletBalanceVO queryWalletBalanceVO = new QueryWalletBalanceVO();

        queryWalletBalanceVO.setBalance(new BigDecimal("8584337"));
        queryWalletBalanceVO.setTokenName("ETH");

        responseResult.setSucc("查询余额成功",queryWalletBalanceVO);
        return responseResult;
    }

    /**
     * 查询ETH及token交易状态
     * @param queryErcTxStatusDTO
     * @return
     */
    @PostMapping(QUERY_ERCTX_STATUS)
    public ResponseResult<String> queryErcTxStatus(@RequestBody QueryErcTxStatusDTO queryErcTxStatusDTO) {
        log.info("************queryErcTxStatus start*************");

        ResponseResult<String> responseResult = new ResponseResult<>();

        String status = "success";

        responseResult.setSucc("查询交易状态成功",status);
        return responseResult;
    }

    /**
     * 查询ETH公链和ETH私链交易的gas
     * @param queryErcTxGasDTO
     * @return
     */
    @PostMapping(QUERY_ERCTX_GAS)
    public ResponseResult<String> queryErcTxGas(@RequestBody QueryErcTxGasDTO queryErcTxGasDTO) {
        log.info("************queryErcTxGas start*************");

        ResponseResult<String> responseResult = new ResponseResult<>();

        String gas = "0.0002";

        responseResult.setSucc("查询交易状态成功",gas);
        return responseResult;
    }

//    /**
//     * 私有链查询token发行者
//     * @param queryTokenDTO
//     * @return
//     */
//    @PostMapping(QUERYTOEKNOWENER)
//    public ResponseResult<QueryTokenVO> queryTokenOwner(@RequestBody QueryTokenDTO queryTokenDTO){
//        log.info("************queryTokenOwner start*************");
//
//        ResponseResult<QueryTokenVO> responseResult = new ResponseResult<>();
//
//        String coinType = queryTokenDTO.getCoinType();
//        String contractAddress = queryTokenDTO.getContractAddress();
//
//        String owner  ="JUYJBAFdasdsdaddasdwerr76786dhgjgjg";
//        QueryTokenVO queryTokenVO = new QueryTokenVO();
//        queryTokenVO.setOwner(owner);
//
//        responseResult.setSucc("查询token发行者成功",queryTokenVO);
//        return responseResult;
//    }

    //    /**
//     * 私有链查询token总数
//     * @param coinType
//     * @param tokenAddress
//     * @return
//     */
//    @GetMapping(QUERYTOKENTOTAL + COINTYPE + TOKENADDRESS)
//    public ResponseResult queryTokenTotal(@PathVariable String coinType,@PathVariable String tokenAddress) {
//        log.info("************queryTokenTotal start*************");
//
//        ResponseResult responseResult = new ResponseResult();
//
//        BalanceVO balanceVO = new BalanceVO();
//        balanceVO.setAddress(tokenAddress);
//        balanceVO.setBalance(new BigDecimal(1289.43902));
//        balanceVO.setCoinType("IONC");
//
//        responseResult.setSucc("获得Token总条数成功",balanceVO);
//        return responseResult;
//    }

}
