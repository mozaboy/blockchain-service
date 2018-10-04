package com.service.btc.service;

import com.service.comm.controller.ResponseResult;
import com.service.comm.model.dto.TransferDTO;
import com.service.comm.model.vo.BTCTransferVO;
import com.service.comm.model.vo.BTCWalletAdressVO;

import java.math.BigDecimal;

public interface IBTCAccoutService {

    ResponseResult<BTCWalletAdressVO> generateWallet();

    BigDecimal getBalance(String strAddress);

    BTCTransferVO BTCtransfer(TransferDTO bitcoinTransferDTO);

    BTCTransferVO USDTtransfer(TransferDTO bitcoinTransferDTO);

}
