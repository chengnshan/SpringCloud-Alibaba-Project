package com.cxp.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cxp.account.pojo.AccountDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author : cheng
 * @date : 2020-08-16 17:20
 */
@Mapper
public interface AccountMapper extends BaseMapper<AccountDTO> {

    int decreaseAccount(@Param("userId") String userId, @Param("amount") BigDecimal amount);
}
