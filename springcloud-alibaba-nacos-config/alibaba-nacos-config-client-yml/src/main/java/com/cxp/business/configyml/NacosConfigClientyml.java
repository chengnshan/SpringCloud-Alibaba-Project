package com.cxp.business.configyml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author : cheng
 * @date : 2020-01-11 11:59
 */
@SpringBootApplication
public class NacosConfigClientyml {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(NacosConfigClientyml.class, args);

        String configName = applicationContext.getEnvironment().getProperty("config.name");
        System.out.println(configName);
    }
}
