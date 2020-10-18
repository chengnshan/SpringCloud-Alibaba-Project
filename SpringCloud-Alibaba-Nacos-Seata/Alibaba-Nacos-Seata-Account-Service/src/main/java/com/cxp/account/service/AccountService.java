package com.cxp.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cxp.account.pojo.AccountDTO;
import com.cxp.common.pojo.BusinessDTO;
import com.cxp.common.response.ObjectResponse;

/**
 * @Author: heshouyou
 * @Description
 * @Date Created in 2019/1/14 17:17
 */
public interface AccountService extends IService<AccountDTO> {

    /**
     * 扣用户钱
     */
    ObjectResponse decreaseAccount(AccountDTO accountDTO);
}
