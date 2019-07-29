package com.cxp.alibabanacosdiscoveryserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 程
 * @date 2019/7/29 下午12:44
 */
@RestController
@Slf4j
public class RestTemplateController {

    @RequestMapping(value = "/restTemplateHandler")
    public String restTemplateHandler(String name){
        log.info("restTemplateHandler name :" + name );
        return name;
    }

}
