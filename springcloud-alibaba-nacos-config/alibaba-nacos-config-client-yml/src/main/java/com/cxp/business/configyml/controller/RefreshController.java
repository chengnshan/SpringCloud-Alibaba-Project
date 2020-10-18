package com.cxp.business.configyml.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 动态刷新配置
 * @author : cheng
 * @date : 2020-01-10 16:55
 */
@Controller
@RefreshScope
public class RefreshController {

    @Value(value = "${config.name}")
    private String configName;

    @RequestMapping(value = "/get")
    @ResponseBody
    public String get(){
        return configName;
    }
}
