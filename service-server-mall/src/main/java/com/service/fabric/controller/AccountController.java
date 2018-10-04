package com.service.fabric.controller;

import com.service.comm.controller.ResponseResult;
import com.service.fabric.vo.FabricAccountVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.service.comm.http.Url.*;

@RestController
@RequestMapping(SERVICE_SERVER_FABRIC + API_VERSION)
public class AccountController {

    private Logger log = LoggerFactory.getLogger(AccountController.class);

    /**
     * 生成Fabric账号
     * @param
     * @return
     */
    @PostMapping(CREATEFABRICACCOUNT)
    public ResponseResult<FabricAccountVO> createFabricAccount(){
        log.info("************createFabricAccount start*************");

        ResponseResult<FabricAccountVO> responseResult = new ResponseResult<>();

        String registerCertificateUrl = "http://file.com/opt/files/certificate/dsdsdfvbrgr.pnf";
        String transactionCertificateUrl = "http://file.com/opt/files/certificate/dsdsdbrgr.pnf ";

        FabricAccountVO fabricAccountVO = new FabricAccountVO();

        fabricAccountVO.setRegisterCertificateUrl(registerCertificateUrl);
        fabricAccountVO.setTransactionCertificateUrl(transactionCertificateUrl);

        responseResult.setSucc("生成Fabric账号成功",fabricAccountVO);
        return responseResult;
    }

}
