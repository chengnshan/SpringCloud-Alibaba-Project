package com.cxp.account.controller;

import com.cxp.account.pojo.AccountDTO;
import com.cxp.account.service.AccountService;
import com.cxp.common.pojo.BusinessDTO;
import com.cxp.common.response.ObjectResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: heshouyou
 * @Description  Dubbo业务执行入口
 * @Date Created in 2019/1/14 17:15
 */
@RestController
@Slf4j
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @PostMapping("/dec_account")
    ObjectResponse decAccount( AccountDTO accountDTO){
        LOGGER.info("请求参数：{}",accountDTO.toString());
        ObjectResponse objectResponse = accountService.decreaseAccount(accountDTO);
        return objectResponse;
    }
}
