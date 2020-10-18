package com.cxp.business.alibabanacosdiscoveryserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 程
 * @date 2019/7/29 下午12:51
 */
@RestController
@Slf4j
public class WebClientController {

    @RequestMapping(value = "/webClientHandler")
    public String webClientHandler(String name){
        log.info("webClientHandler name :" + name );
        return name;
    }

}
