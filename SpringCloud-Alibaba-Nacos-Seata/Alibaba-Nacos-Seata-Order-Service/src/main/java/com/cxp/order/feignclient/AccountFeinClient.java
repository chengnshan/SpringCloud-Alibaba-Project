package com.cxp.order.feignclient;

import com.cxp.common.response.ObjectResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author : cheng
 * @date : 2020-08-16 17:45
 */
@FeignClient(name = "account-service", fallback = AccountFeinClient.AccountFeignClientFallback.class)
public interface AccountFeinClient {

    @PostMapping(value = "/account/dec_account")
    ObjectResponse decAccount(@RequestParam String userId, @RequestParam BigDecimal amount);

    @Component
    class AccountFeignClientFallback implements AccountFeinClient {

        @Override
        public ObjectResponse decAccount(String userId, BigDecimal amount) {
            ObjectResponse response = new ObjectResponse();
            response.setMessage("请求失败，请稍候重试!");
            return response;
        }
    }
}
