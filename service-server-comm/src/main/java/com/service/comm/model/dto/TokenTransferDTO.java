package com.service.comm.model.dto;

import com.service.comm.util.ResponseMsg;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class TokenTransferDTO
{
    @NotNull
    @NotEmpty(message = ResponseMsg.TRANSFER_FROM_ADDRESS_NULL_ERROR)
    private String fromAddress;     //转出地址
    @NotNull
    @NotEmpty(message = ResponseMsg.SEND_COIN_SEND_ADDRESS_NULL_ERROR)
    private String toAddress;       //转入地址
    @NotNull
    @NotEmpty(message = ResponseMsg.TRANSFER_AMOUNT_NULL_ERROR)
    @Min(1)
    @Max(100000)
    private String transferAmout;   //转账金额
    @NotNull
    @NotEmpty(message = ResponseMsg.PASS_WORD_NULL_ERROR)
    private String password;        //密码
    @NotNull
    @NotEmpty(message = ResponseMsg.PATH_NULL_ERROR)
    private String path;            //路径
    @NotNull
    @NotEmpty(message = ResponseMsg.KEY_STORE_NULL_ERROR)
    private String keyStore;        //密钥库
    @NotNull
    @NotEmpty(message = ResponseMsg.COIN_TYPE_NULL_ERROR)
    private String coinType;        //币种
    @NotNull
    @NotEmpty(message = ResponseMsg.CONTRACT_ADDRESS_NULL_ERROR)
    private String contractAddress; //合约地址

    private String gasLimit;        //燃油费限制
}
