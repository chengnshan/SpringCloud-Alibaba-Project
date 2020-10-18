package com.cxp.business.alibabanacosdiscoveryclientcommon.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author 程
 * @date 2019/7/29 下午12:53
 */
@RestController
@Slf4j
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    Client client;

    @GetMapping("/testRestTemplate")
    public String testRestTemplate() {
        String result = restTemplate.getForObject(
                "http://alibaba-nacos-discovery-server/discovery-server/restTemplateHandler" +
                "?name=restTemplateHandler", String.class);
        log.info("testRestTemplate name : "+ result);
        return "Return : " + result;
    }

    @GetMapping("/testWebClient")
    public String testWebClient() {
        Mono<String> result = webClientBuilder.build().get()
                .uri("http://alibaba-nacos-discovery-server/discovery-server/webClientHandler?name=webClientHandler")
                .retrieve()
                .bodyToMono(String.class);
        log.info("testWebClient result : "+ result.block());
        return "Return : " + result.block();
    }

    @GetMapping("/testFeign")
    public String testFeign() {
        String result = client.testFeign("feignHandler");
        log.info("testFeign result : "+ result);
        return "Return : " + result;
    }

    @FeignClient(value = "alibaba-nacos-discovery-server")
    interface Client{

        @GetMapping(value = "/discovery-server/feignHandler")
        public String testFeign(@RequestParam(value = "name")String name);
    }
}
