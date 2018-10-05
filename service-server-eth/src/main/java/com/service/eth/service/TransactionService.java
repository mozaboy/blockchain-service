package com.service.eth.service;

import com.service.comm.model.dto.ETHTransferDTO;
import com.service.comm.model.dto.TokenTransferDTO;
import com.service.comm.model.vo.ETHTransferVO;
import com.service.comm.model.vo.TokenTransferVO;
import org.web3j.crypto.Credentials;

public interface TransactionService
{
//    String transaction(TransferDTO transferDTO);

    // ETH转账
    ETHTransferVO getEthTransfer(ETHTransferDTO ethTransferDTO);

    // token转账
    TokenTransferVO getTokenTransfer(TokenTransferDTO tokenTransferDTO);

    // eth加载钱包凭证
    Credentials loadWalletCredentials(ETHTransferDTO ethTransferDTO);

    // token加载钱包凭证
    Credentials loadWalletCredentials(TokenTransferDTO tokenTransferDTO);



}
