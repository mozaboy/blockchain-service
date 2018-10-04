package com.service.eth.service.impl;

import com.service.comm.model.dto.ETHTransferDTO;
import com.service.comm.model.dto.TokenTransferDTO;
import com.service.comm.model.vo.ETHTransferVO;
import com.service.comm.model.vo.TokenTransferVO;
import com.service.eth.config.EthConfig;
import com.service.eth.controller.AddressController;
import com.service.eth.service.TransactionService;
import com.service.eth.utils.Web3JConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

@Service
public class TransactionServiceImpl implements TransactionService
{

    @Autowired
    private Web3JConnector web3JConnector;
    @Autowired
    private EthConfig ethconfig;

    private Logger log = LoggerFactory.getLogger(AddressController.class);

    /**
     * eth转账
     * @param ethTransferDTO
     * @return
     */
    @Override
    public ETHTransferVO getEthTransfer(ETHTransferDTO ethTransferDTO) {
        if (ethTransferDTO == null || "".equals(ethTransferDTO)) {
            log.error("参数错误");
            return null;
        }

        String strFromAddress = ethTransferDTO.getFromAddress();    //转出地址
        String strToAddress = ethTransferDTO.getToAddress();        //转入地址
        String strTransferAmout = ethTransferDTO.getTransferAmout();//转账金额
        String strGas = ethTransferDTO.getGasLimit();               //燃油费

        try {
            BigInteger val = Convert.toWei(strTransferAmout, Convert.Unit.ETHER).toBigInteger();//转多少金额
            EthGetTransactionCount ethGetTransactionCount = web3JConnector.buildWeb3j().ethGetTransactionCount(
                    strFromAddress,
                    DefaultBlockParameterName.PENDING).sendAsync().get();
            if (ethGetTransactionCount.getResult() == null) {
                log.error("ethGetTransactionCount is null");
                return null;
            }
            BigInteger nonce = ethGetTransactionCount.getTransactionCount().add(BigInteger.ONE);
            BigInteger gasPrice = new BigInteger(Convert.toWei(ethconfig.getGasPrice().toString(), Convert.Unit.GWEI).toPlainString());
            BigInteger gasLimit = ethconfig.getGasLimit();
            //sign
            //发送一个已经签名的交易
            RawTransaction rawTransaction1 = RawTransaction.createEtherTransaction(nonce, gasPrice, gasLimit, strToAddress, val);

            //通过密码，路径+keyStore 生成凭证
            Credentials credentials = loadWalletCredentials(ethTransferDTO);

            byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction1,credentials);
            String hexValue = Numeric.toHexString(signedMessage);
            EthSendTransaction ethSendTransaction = web3JConnector.buildWeb3j().ethSendRawTransaction(hexValue).sendAsync().get();
            String txHash = ethSendTransaction.getTransactionHash();
            ETHTransferVO ethTransferVO = new ETHTransferVO();
            ethTransferVO.setTxHash(txHash);
            return ethTransferVO;
        } catch (Exception e) {
            log.error("error about transactionReceipt" + e);
        }
        return null;
    }

    /**
     * token转账
     * @param tokenTransferDTO
     * @return
     */
    @Override
    public TokenTransferVO getTokenTransfer(TokenTransferDTO tokenTransferDTO) {
        String contractAddr = tokenTransferDTO.getContractAddress();
        Double dTransAmount = Double.parseDouble(tokenTransferDTO.getTransferAmout());
//        double decimal = EnumCurrencyInfo.getByCode(Integer.valueOf(CoinType)).getDecimal();  //2
        double decimal = 2;
        Double decimalPow = Math.pow(10, decimal);  //100
        Double dValue = dTransAmount * decimalPow;  //17700.0
        java.text.NumberFormat NF = java.text.NumberFormat.getInstance();
        NF.setGroupingUsed(false);
        String strValue = NF.format(dValue);
        BigInteger bigInAmount = new BigInteger(strValue);
        BigDecimal bigDeGasPrice = Convert.toWei(ethconfig.getGasPrice().toString(), Convert.Unit.GWEI);

        EthGetTransactionCount ethGetTransactionCount = null;
        try {
            ethGetTransactionCount = web3JConnector.buildWeb3j().ethGetTransactionCount(
                    tokenTransferDTO.getFromAddress(),
                    DefaultBlockParameterName.PENDING).sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }

        if (ethGetTransactionCount.getResult() == null) {
            log.error("ethGetTransactionCount is null");
            return null;
        }

        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        Function function = new Function(
                "transfer",
                Arrays.<Type>asList(new Address(tokenTransferDTO.getToAddress()),
                        new Uint256(bigInAmount)),
                Collections.<TypeReference<?>>emptyList());
        String encodedFunction = FunctionEncoder.encode(function);
        Credentials credentials = loadWalletCredentials(tokenTransferDTO);
        RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, new BigInteger(bigDeGasPrice.toPlainString()), ethconfig.getGasLimit(), contractAddr, encodedFunction);
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);
        EthSendTransaction ethSendTransaction = null;
        try {
            ethSendTransaction = web3JConnector.buildWeb3j().ethSendRawTransaction(hexValue).sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        String txHash = ethSendTransaction.getTransactionHash();
        if (txHash == null) {
            log.error("error in tokenTransaction for userId: " );
            return null;
        }
        TokenTransferVO tokenTransferVO = new TokenTransferVO();
        tokenTransferVO.setTxHash(txHash);

        return tokenTransferVO;
    }


    /**
     * 加载钱包凭证
     * @param ethTransferDTO
     * @return
     */
    @Override
    public Credentials loadWalletCredentials(ETHTransferDTO ethTransferDTO)
    {
        if (ethTransferDTO == null) {
            return null;
        }

        //加载钱包文件
        Credentials credentials;
        try {
            credentials = WalletUtils.loadCredentials(ethTransferDTO.getPassword(), ethTransferDTO.getPath() + ethTransferDTO.getKeyStore());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return credentials;
    }



    /**
     * 加载钱包凭证
     * @param tokenTransferDTO
     * @return
     */
    @Override
    public Credentials loadWalletCredentials(TokenTransferDTO tokenTransferDTO)
    {
        if (tokenTransferDTO == null) {
            return null;
        }

        //加载钱包文件
        Credentials credentials;
        try {
            credentials = WalletUtils.loadCredentials(tokenTransferDTO.getPassword(), tokenTransferDTO.getPath() + tokenTransferDTO.getKeyStore());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return credentials;
    }


    /**
     * token转账 1-充币 2-提币 3-以太坊归集
     *
     * @param transferDTO
     *
     * @return
     */
//    @Override
//    public String transaction(TransferDTO transferDTO)
//    {
//        if (transferDTO == null) {
//            log.error("invalid args for ethTransaction");
//            return null;
//        }
//
//        //获得币种相应信息
//        EnumCurrencyInfo enumCurrencyInfo = EnumCurrencyInfo.getByCode(Integer.valueOf(transferDTO.getCoinType()));
//        HotWalletDO hotWalletDO = hotWalletService.getHotWalletInfo(enumCurrencyInfo.getCode());
//
//        /*转账类型*/
//        EnumCoinTxHashType enumCoinTxHashType = EnumCoinTxHashType.getByCode(Integer.parseInt(transferDTO.getTransferType()));
//        switch (enumCoinTxHashType) {
//            case WITHDRAW:
//                DcAddressETH dcAddressETH = walletAddressInfoService.getEthAddressInfo(hotWalletDO.getAddress());
//                Credentials credentialsHotWallet = loadWalletCredentials(dcAddressETH);
//                if (credentialsHotWallet == null) {
//                    return null;
//                }
//
//                TransferDTO ethTransferDTOWithDraw = new TransferDTO();
//                BeanUtils.copyProperties(transferDTO, ethTransferDTOWithDraw);
//
//                //计算实际转账数额和用户扣除数据
//                BigDecimal bigDeTransAmount = new BigDecimal(ethTransferDTO.getTransferAmount());
//                BigDecimal bigDeFee = new BigDecimal(ethTransferDTO.getFee());
//                if (bigDeTransAmount.compareTo(bigDeFee) <= 0) {
//                    log.error("error： transAmount could not equal to fee");
//                    return null;
//                }
//                BigDecimal realTransAmount = bigDeTransAmount.subtract(bigDeFee); //转账实际
//                ethTransferDTO.setTransferAmount(realTransAmount.toPlainString());
//                ethTransferDTO.setFromAddress(hotWalletDO.getAddress());
//                //转账参数
//
//                String userAddress = userDepositAddrService.getUserAddressByUid(ethTransferDTO.getUserId(), ETH.getCode()).getAddress();//根据uid查询用户eth地址
//
//                if (ethTransferDTO.getCoinType().equals(ETH.getCode().toString())) {
//                    //转账
//                    String txHashWithDraw = ethSimpleTransaction(credentialsHotWallet, ethTransferDTO);
//                    if (txHashWithDraw == null) {
//                        return null;
//                    }
//                    // 写入提币流水表
//                    ethTransferDTOWithDraw.setFromAddress(userAddress);
//                    addDcWithdrawRecord(ethTransferDTOWithDraw, txHashWithDraw, realTransAmount);
//
//                    return txHashWithDraw;
//                } else {
//                    //token提币
//                    String txId = tokenSimpleTransaction(credentialsHotWallet,
//                            ethTransferDTO,
//                            ethTransferDTO.getCoinType());
//                    if (txId == null) {
//                        return null;
//                    }
//
//                    //写入提币流水表
//                    ethTransferDTOWithDraw.setFromAddress(userAddress);
//                    addDcWithdrawRecord(ethTransferDTOWithDraw, txId, realTransAmount);
//                    return txId;
//                }
//
//            case COLLECTION_FEE:
//                DcAddressETH ethHotAddressInfo = walletAddressInfoService.getEthAddressInfo(hotWalletDO.getAddress());
//                credentialsHotWallet = loadWalletCredentials(ethHotAddressInfo);
//                if (credentialsHotWallet == null) {
//                    return null;
//                }
//
//                ETHTransferDTO ethTransferDTOFee = ethTransferDTO;
//                ethTransferDTOFee.setFromAddress(ethHotAddressInfo.getAddress());
//                ethTransferDTOFee.setToAddress(ethTransferDTO.getToAddress());
//                ethTransferDTOFee.setTransferAmount(ethConfig.getFee().toString());
//                String txIdForFee = ethSimpleTransaction(credentialsHotWallet, ethTransferDTO);
//                if (txIdForFee == null) {
//                    return null;
//                }
//                return txIdForFee;
//            case COLLECTION:
//                DcAddressETH collAddressInfo = walletAddressInfoService.getEthAddressInfo(ethTransferDTO.getFromAddress());
//                Credentials credentialsUserAddress = loadWalletCredentials(collAddressInfo);
//                if (credentialsUserAddress == null) {
//                    return null;
//                }
//
//                if (ethTransferDTO.getCoinType().equals(ETH.getCode().toString())) {
//                    BigDecimal amountSubFee = new BigDecimal(ethTransferDTO.getTransferAmount()).subtract(ethConfig.getFee()); //保留0.01作为手续费
//                    ethTransferDTO.setTransferAmount(amountSubFee.toPlainString()); // TODO: 2018/7/5 扣除手续费
//                    //转账
//                    String txHashCollection = ethSimpleTransaction(credentialsUserAddress, ethTransferDTO);
//                    if (txHashCollection == null) {
//                        return null;
//                    }
//
//                    log.info("token Collection transaction is done: userId:{}, txHash:{}, fromAddr:{}, toAddr:{}, amount:{}," +
//                                    ethTransferDTO.getUserId(),
//                            txHashCollection,
//                            ethTransferDTO.getTransferType(),
//                            ethTransferDTO.getFromAddress(),
//                            ethTransferDTO.getToAddress(),
//                            ethTransferDTO.getTransferAmount());
//                    return txHashCollection;
//                } else {
//                    String txId = tokenSimpleTransaction(credentialsUserAddress, ethTransferDTO, ethTransferDTO.getCoinType());
//                    if (txId == null) {
//                        return null;
//                    }
//
//                    log.info("token Collection transaction is done: userId:{}, txHash:{}, fromAddr:{}, toAddr:{}, amount:{}," +
//                                    ethTransferDTO.getUserId(),
//                            txId,
//                            ethTransferDTO.getTransferType(),
//                            ethTransferDTO.getFromAddress(),
//                            ethTransferDTO.getToAddress(),
//                            ethTransferDTO.getTransferAmount());
//                    return txId;
//                }
//
//            default:
//                log.error("not found transferType");
//                return null;
//        }
//    }
//
//    /**
//     * Token转账
//     *
//     * @param
//     */
//    public String tokenSimpleTransaction(Credentials credentials, ETHTransferDTO ethTransferDTO, String CoinType)
//    {
//        String contractAddr = EnumCurrencyInfo.getByCode(Integer.valueOf(CoinType)).getContractAddress();
//        Double dTransAmount = Double.parseDouble(ethTransferDTO.getTransferAmount());
//        double decimal = EnumCurrencyInfo.getByCode(Integer.valueOf(CoinType)).getDecimal();  //2
//        Double decimalPow = Math.pow(10, decimal);  //100
//        Double dValue = dTransAmount * decimalPow;  //17700.0
//        java.text.NumberFormat NF = java.text.NumberFormat.getInstance();
//        NF.setGroupingUsed(false);
//        String strValue = NF.format(dValue);
//        BigInteger bigInAmount = new BigInteger(strValue);
//        BigDecimal bigDeGasPrice = Convert.toWei(ethconfig.getGasPrice().toString(), Convert.Unit.GWEI);
//
//        EthGetTransactionCount ethGetTransactionCount = null;
//        try {
//            ethGetTransactionCount = web3JConnector.buildWeb3j().ethGetTransactionCount(
//                    ethTransferDTO.getFromAddress(),
//                    DefaultBlockParameterName.PENDING).sendAsync().get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            return null;
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//        if (ethGetTransactionCount.getResult() == null) {
//            log.error("ethGetTransactionCount is null");
//            return null;
//        }
//
//        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
//        Function function = new Function(
//                "transfer",
//                Arrays.<Type>asList(new Address(ethTransferDTO.getToAddress()),
//                        new Uint256(bigInAmount)),
//                Collections.<TypeReference<?>>emptyList());
//        String encodedFunction = FunctionEncoder.encode(function);
//        RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, new BigInteger(bigDeGasPrice.toPlainString()), ethconfig.getGasLimit(), contractAddr, encodedFunction);
//        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
//        String hexValue = Numeric.toHexString(signedMessage);
//        EthSendTransaction ethSendTransaction = null;
//        try {
//            ethSendTransaction = web3JConnector.buildWeb3j().ethSendRawTransaction(hexValue).sendAsync().get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        String txHash = ethSendTransaction.getTransactionHash();
//        if (txHash == null) {
//            log.error("error in tokenTransaction for userId: " + ethTransferDTO.getUserId());
//            return null;
//        }
//
//        //写入转账记录表
//        transferLog(ethTransferDTO, txHash);
//        return txHash;
//    }
//
//
//    /**
//     * eth转账
//     *
//     * @param credentials
//     * @param ethTransferDTO
//     *
//     * @return
//     */
//    @Override
//    public String ethSimpleTransaction(Credentials credentials, ETHTransferDTO ethTransferDTO)
//    {
//        if (credentials == null || ethTransferDTO == null) {
//            log.error("invalid args for EthSimpleTransaction");
//            return null;
//        }
//
//        try {
//            BigInteger value = Convert.toWei(ethTransferDTO.getTransferAmount(), Convert.Unit.ETHER).toBigInteger();
//            //get nonce
//            EthGetTransactionCount ethGetTransactionCount = web3JConnector.buildWeb3j().ethGetTransactionCount(
//                    ethTransferDTO.getFromAddress(),
//                    DefaultBlockParameterName.PENDING).sendAsync().get();
//
//            if (ethGetTransactionCount.getResult() == null) {
//                log.error("ethGetTransactionCount is null");
//                return null;
//            }
//
//            BigInteger nonce = ethGetTransactionCount.getTransactionCount().add(BigInteger.ONE);
//            BigInteger gasPrice = new BigInteger(Convert.toWei(ethconfig.getGasPrice().toString(), Convert.Unit.GWEI).toPlainString());
//            BigInteger gasLimit = ethconfig.getGasLimit();
//            //sign
//            RawTransaction rawTransaction1 = RawTransaction.createEtherTransaction(
//                    nonce,
//                    gasPrice,
//                    gasLimit,
//                    ethTransferDTO.getToAddress(),
//                    value);
//
//            byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction1, credentials);
//            String hexValue = Numeric.toHexString(signedMessage);
//            EthSendTransaction ethSendTransaction = web3JConnector.buildWeb3j().ethSendRawTransaction(hexValue).sendAsync().get();
//            String txHash = ethSendTransaction.getTransactionHash();
//
//            //写入转账记录表
//            transferLog(ethTransferDTO, txHash);
//            return txHash;
//        } catch (Exception e) {
//            log.error("error about transactionReceipt" + e);
//            return null;
//        }
//    }
}
