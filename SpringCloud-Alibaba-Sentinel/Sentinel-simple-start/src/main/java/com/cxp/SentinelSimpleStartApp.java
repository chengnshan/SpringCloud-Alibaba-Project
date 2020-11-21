package com.cxp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author : cheng
 * @date : 2020-10-18 19:38
 */
@SpringBootApplication
@EnableAsync
public class SentinelSimpleStartApp {
    public static void main(String[] args) {
        SpringApplication.run(SentinelSimpleStartApp.class, args);
    }
}
