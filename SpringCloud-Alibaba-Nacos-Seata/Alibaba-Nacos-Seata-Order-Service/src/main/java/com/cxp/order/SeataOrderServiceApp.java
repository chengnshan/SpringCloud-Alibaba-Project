package com.cxp.order;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.cxp",exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableAutoDataSourceProxy
@EnableFeignClients(basePackages = "com.cxp.order.feignclient")
public class SeataOrderServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(SeataOrderServiceApp.class, args);
    }
}
