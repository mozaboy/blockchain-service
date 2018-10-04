package com.service.comm.model.dto;

import lombok.Data;
import springfox.documentation.spring.web.json.Json;

@Data
public class ImportWalletAddressDTO
{
    private Json keyStore;          //密钥库
    private String password;        //密码
    private String mnemonicWord;    //助记词
}
