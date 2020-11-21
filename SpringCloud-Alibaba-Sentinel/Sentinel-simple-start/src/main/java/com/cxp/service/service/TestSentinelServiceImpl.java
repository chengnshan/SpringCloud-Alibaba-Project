package com.cxp.service.service;

import com.cxp.service.TestSentinelService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author : cheng
 * @date : 2020-10-19 22:15
 */
@Service(value = "testSentinelService")
public class TestSentinelServiceImpl implements TestSentinelService {

    @Async
    @Override
    public void asyncSentinel() {
        System.out.println("异步调用开始.");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("异步调用结束.");
    }
}
