package com.service.comm.model.vo;

import lombok.Data;
import springfox.documentation.spring.web.json.Json;

@Data
public class CreatePublicAccountVO {

    private String accountAddress;
    private String secretKey;
    private String mnemonicWord;
}
