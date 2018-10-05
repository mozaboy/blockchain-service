package com.service.mall.controller;

import com.service.comm.controller.ResponseResult;
import com.service.mall.vo.CochainVO;
import com.service.mall.vo.FabricAccountVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.service.comm.http.Url.*;

@RestController
@RequestMapping(SERVICE_SERVER_FABRIC + API_VERSION)
public class CochainController {

    private Logger log = LoggerFactory.getLogger(CochainController.class);

    /**
     * 资产信息上链
     * @param
     * @return
     */
    @PostMapping(INFOCOCHAIN)
    public ResponseResult<FabricAccountVO> infoCochain(@RequestBody CochainVO cochainVO){
        log.info("************infoCochain start*************");

        ResponseResult<FabricAccountVO> responseResult = new ResponseResult<>();

        String registerCertificateUrl = "http://file.com/opt/files/certificate/dsdsdfvbrgr.pnf";
        String transactionCertificateUrl = "http://file.com/opt/files/certificate/dsdsdbrgr.pnf ";

        FabricAccountVO fabricAccountVO = new FabricAccountVO();

        fabricAccountVO.setRegisterCertificateUrl(registerCertificateUrl);
        fabricAccountVO.setTransactionCertificateUrl(transactionCertificateUrl);

        responseResult.setSucc("生成Fabric账号成功",fabricAccountVO);
        return responseResult;
    }

    /**
     * 资产报告上链
     * @param
     * @return
     */
    @PostMapping(FILECOCHAIN)
    public ResponseResult<FabricAccountVO> fileCochain (@RequestBody CochainVO cochainVO){
        log.info("************fileCochain  start*************");

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
