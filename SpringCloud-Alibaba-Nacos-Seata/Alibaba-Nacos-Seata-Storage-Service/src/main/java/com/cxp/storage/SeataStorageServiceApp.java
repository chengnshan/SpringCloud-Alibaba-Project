package com.cxp.storage;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.cxp",exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableAutoDataSourceProxy
@EnableFeignClients
public class SeataStorageServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(SeataStorageServiceApp.class, args);
    }
}
