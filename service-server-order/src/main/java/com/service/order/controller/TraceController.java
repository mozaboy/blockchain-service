package com.service.order.controller;

import com.service.comm.controller.ResponseResult;
import com.service.order.vo.TraceVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import static com.service.comm.http.Url.*;

@RestController
@RequestMapping(SERVICE_SERVER_FABRIC + API_VERSION)
public class TraceController {

    private Logger log = LoggerFactory.getLogger(TraceController.class);

    /**
     * 资产信息溯源
     * @param
     * @return
     */
    @GetMapping(ASSETINFOTRACE + TRACEHASH)
    public ResponseResult<TraceVO> assetInfoTrace(@PathVariable String traceHash){
        log.info("************assetInfoTrace start*************");

        ResponseResult<TraceVO> responseResult = new ResponseResult<>();

        TraceVO traceVO = new TraceVO();
        traceVO.setDigestHash("ds8fsd8fskdf0asd7Hffsd75fs897fsdfsfsdkfsi9654asfasfd");
        traceVO.setSignatureInfo("jhjkdf0asd7Hffsd75fs897fsdffdf45jlko0896sdsq4asfasfd");
        traceVO.setTimestampStr("2018-09-26 17:41:32");

        responseResult.setSucc("追溯成功",traceVO);
        return responseResult;
    }

    /**
     * 资产报告溯源
     * @param
     * @return
     */
    @GetMapping(CERTIFICATETRACE + TRACEHASH)
    public ResponseResult<TraceVO> certificateTrace(@PathVariable String traceHash){
        log.info("************certificateTrace start*************");

        ResponseResult<TraceVO> responseResult = new ResponseResult<>();

        TraceVO traceVO = new TraceVO();
        traceVO.setDigestHash("ds8fsd8fskdf0asd7Hffsd75fs897fsdfsfsdkfsi9654asfasfd");
        traceVO.setSignatureInfo("jhjkdf0asd7Hffsd75fs897fsdffdf45jlko0896sdsq4asfasfd");
        traceVO.setTimestampStr("2018-09-26 17:41:32");

        responseResult.setSucc("追溯成功",traceVO);
        return responseResult;
    }

}
