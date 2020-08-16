package com.cxp.common.config;

import io.seata.spring.annotation.GlobalTransactionScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : cheng
 * @date : 2020-08-15 21:01
 */
@Configuration
public class GlobalTransactionScannerConfig {

    @Value("${spring.application.name}")
    private String applicationId ;
    @Value("${seata.tx-service-group}")
    private String txServiceGroup ;

    @Bean
    public GlobalTransactionScanner globalTransactionScanner(){
        return new GlobalTransactionScanner(applicationId,txServiceGroup);
    }
}
