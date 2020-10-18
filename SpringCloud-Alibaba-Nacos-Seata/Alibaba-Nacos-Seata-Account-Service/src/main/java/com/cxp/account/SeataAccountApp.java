package com.cxp.account;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author cheng
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.cxp",exclude = DataSourceAutoConfiguration.class)
@EnableAutoDataSourceProxy
public class SeataAccountApp {
    public static void main(String[] args) {
        SpringApplication.run(SeataAccountApp.class, args);
    }
}
