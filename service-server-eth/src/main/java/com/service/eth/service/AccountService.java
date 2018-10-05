package com.service.eth.service.eth.service;

import java.util.Map;

public interface AccountService {

    Map<String, Object> createERCAccount();

    //eth创建钱包地址 带参数
    Map<String, Object> createERCAccount(String password);
}
