package com.service.eth.service.eth.service.impl;

import com.service.comm.util.BalanceUtil;
import com.service.eth.service.eth.config.EthConfig;
import com.service.eth.service.eth.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.WalletUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private EthConfig ethConfig;

    private Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Override
    public Map<String, Object> createERCAccount() {

        String strCode = BalanceUtil.generateRandomCharacter(15);
        String strFullWalletFileName;
        try {
            strFullWalletFileName = WalletUtils.generateFullNewWalletFile(strCode, new File(ethConfig.getNewWalletPath()));
        } catch (Exception e) {
            log.error("error in IOException " + e);
            return null;
        }

        String strWalletFileNameNoPrefix = strFullWalletFileName.substring(0, strFullWalletFileName.lastIndexOf("."));
        String[] strArrWalletFileNames = strWalletFileNameNoPrefix.split("--");
        String strAddress = "0x" + strArrWalletFileNames[2];

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("address", strAddress);
        resultMap.put("keystore", strFullWalletFileName);

        return resultMap;
    }

    @Override
    public Map<String, Object> createERCAccount(String password)
    {

        String strFullWalletFileName;
        File file = new File(ethConfig.getNewWalletPath());
        if (!file.exists()){
            file.mkdirs();
        }

        if ("".equals(password) || password == null){
            String strCode = BalanceUtil.generateRandomCharacter(15);
            try {

                strFullWalletFileName = WalletUtils.generateFullNewWalletFile(strCode,file);
            }catch (Exception e){
                log.error("error in IOException"+ e);
                return null;
            }
        }else {
            try {
                strFullWalletFileName = WalletUtils.generateFullNewWalletFile(password, new File(ethConfig.getNewWalletPath()));
            } catch (Exception e) {
                log.error("error in IOException " + e);
                return null;
            }
        }

        String strWalletFileNameNoPrefix = strFullWalletFileName.substring(0, strFullWalletFileName.lastIndexOf("."));
        String[] strArrWalletFileNames = strWalletFileNameNoPrefix.split("--");
        String strAddress = "0x" + strArrWalletFileNames[2];

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("address", strAddress);
        resultMap.put("keystore", strFullWalletFileName);

        return resultMap;
    }

}
