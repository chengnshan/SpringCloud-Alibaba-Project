package com.cxp.alibabanacosdiscoveryserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 程
 * @date 2019/7/29 下午12:52
 */
@RestController
@Slf4j
public class FeignController {

    @RequestMapping(value = "/feignHandler")
    public String feignHandler(String name){
        log.info("feignHandler name :" + name );
        return name;
    }
}
