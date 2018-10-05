package com.service.eth.controller;

import com.service.comm.controller.ResponseResult;
import com.service.comm.model.dto.CreateAddressDTO;
import com.service.comm.model.dto.ImportWalletAddressDTO;
import com.service.comm.model.vo.CreateAddressVO;
import com.service.comm.model.vo.CreatePublicAccountVO;
import com.service.eth.service.eth.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.service.comm.http.Url.*;
/**
 * ETH地址相关controller
 */
@RestController
@RequestMapping(SERVICE_SERVER_ETH + API_VERSION)
public class AddressController {


    @Autowired
    private AccountService accountService;

    private Logger log = LoggerFactory.getLogger(AddressController.class);

    /**createPrivateAddress
     * ETH私链钱包地址
     * @return
     */
    @PostMapping(CREATE_PRIVATE_ADDRESS)
    public ResponseResult<CreateAddressVO> createPrivateAddress(@RequestBody CreateAddressDTO createAddressDTO) {
        log.info("************createPrivateAddress start*************");

        ResponseResult<CreateAddressVO> createAddressResult = new ResponseResult<>();
        String password = createAddressDTO.getPassword();

        //创建钱包地址
        Map<String, Object> quantit = accountService.createERCAccount(password);
        if (quantit == null){
            createAddressResult.setFail("创建ETH私链钱包地址失败");
            return createAddressResult;
        }
        String qu =(String) quantit.get("address");
        String ww =(String) quantit.get("keystore");

        CreateAddressVO createAddressVO = new CreateAddressVO();

        createAddressVO.setAccountAddress(qu);
        createAddressVO.setSecretKey(ww);
        if (createAddressVO == null){
            createAddressResult.setFail("创建ETH私链钱包地址失败");
            return createAddressResult;
        }
        createAddressResult.setSucc("创建钱包成功",createAddressVO);

        return createAddressResult;
    }

    /**
     * ETH公链钱包地址
     * @return
     */
    @PostMapping(CREATE_PUBLIC_ACCOUNT)
    public ResponseResult<CreatePublicAccountVO> createPublicAccount(@RequestBody CreateAddressDTO createAddressDTO){
        log.info("************createPublicAccount start*************");

        ResponseResult<CreatePublicAccountVO> createAddressResult = new ResponseResult<>();
        String password = createAddressDTO.getPassword();

        //创建钱包地址
        Map<String, Object> quantit = accountService.createERCAccount(password);
        if (quantit == null){
            createAddressResult.setFail("创建ETH公链钱包地址失败");
            return createAddressResult;
        }
        String qu =(String) quantit.get("address");
        String ww =(String) quantit.get("keystore");

        CreatePublicAccountVO createAddressVO = new CreatePublicAccountVO();

        createAddressVO.setAccountAddress(qu);
        createAddressVO.setSecretKey(ww);
        if (createAddressVO == null){
            createAddressResult.setFail("创建ETH公链钱包地址失败");
            return createAddressResult;
        }
        createAddressResult.setSucc("创建钱包成功",createAddressVO);
        return createAddressResult;
    }


    /**
     * 创建糖果钱包
     * @param createAddressDTO
     * @return
     */
    @PostMapping(CRETAE_CANDY_ACCOUNT)
    public ResponseResult<CreateAddressVO> cretaeCandyAccount(@RequestBody CreateAddressDTO createAddressDTO){
        log.info("************createCandyAccount start*************");

        ResponseResult<CreateAddressVO> createAddressResult = new ResponseResult<>();
        String password = createAddressDTO.getPassword();

        //创建钱包地址
        Map<String, Object> quantit = accountService.createERCAccount(password);
        if (quantit == null){
            createAddressResult.setFail("创建糖果钱包失败");
            return createAddressResult;
        }
        String qu =(String) quantit.get("address");
        String ww =(String) quantit.get("keystore");

        CreateAddressVO createAddressVO = new CreateAddressVO();

        createAddressVO.setAccountAddress(qu);
        createAddressVO.setSecretKey(ww);
        if (createAddressVO == null){
            createAddressResult.setFail("创建糖果钱包失败");
            return createAddressResult;
        }
        createAddressResult.setSucc("创建钱包成功",createAddressVO);
        return createAddressResult;
    }

    /**
     * 私链导入ETH钱包地址
     * @param importWalletAddressDTO
     * @return
     */
    @PostMapping(IMPORT_PRIVATE_WALLET_ADDRESS)
    public ResponseResult<Boolean> importPrivateWalletAddress(@RequestBody ImportWalletAddressDTO importWalletAddressDTO){
        log.info("************importPrivateWalletAddress start*************");

        ResponseResult<Boolean> responseResult = new ResponseResult<>();
        Boolean result = true;
        responseResult.setSucc("导入成功",result);
        return responseResult;
    }

    /**
     * 公链导入ETH钱包地址
     * @param importWalletAddressDTO
     * @return
     */
    @PostMapping(IMPORT_PUBLIC_WALLET_ADDRESS)
    public ResponseResult<Boolean> importPublicWalletAddress(@RequestBody ImportWalletAddressDTO importWalletAddressDTO){
        log.info("************importPublicWalletAddress start*************");

        ResponseResult<Boolean> responseResult = new ResponseResult<>();
        Boolean result = true;
        responseResult.setSucc("导入成功",result);
        return responseResult;
    }
}
