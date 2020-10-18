package com.cxp.account.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxp.account.mapper.AccountMapper;
import com.cxp.account.pojo.AccountDTO;
import com.cxp.account.service.AccountService;
import com.cxp.common.enums.RspStatusEnum;
import com.cxp.common.response.ObjectResponse;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.api.transaction.Propagation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, AccountDTO> implements AccountService {

    @Override
    public ObjectResponse decreaseAccount(AccountDTO accountDTO) {
        System.out.println("AccountServiceImpl, 开始全局事务，XID = " + RootContext.getXID());
        int account = baseMapper.decreaseAccount(accountDTO.getUserId(), accountDTO.getAmount());
        ObjectResponse<Object> response = new ObjectResponse<>();
        if (account > 0) {
            response.setStatus(RspStatusEnum.SUCCESS.getCode());
            response.setMessage(RspStatusEnum.SUCCESS.getMessage());
            return response;
        }

        response.setStatus(RspStatusEnum.FAIL.getCode());
        response.setMessage(RspStatusEnum.FAIL.getMessage());
        return response;
    }
}
