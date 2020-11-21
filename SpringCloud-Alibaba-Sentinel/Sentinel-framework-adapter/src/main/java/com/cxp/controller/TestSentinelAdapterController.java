package com.cxp.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.adapter.okhttp.SentinelOkHttpConfig;
import com.alibaba.csp.sentinel.adapter.okhttp.SentinelOkHttpInterceptor;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author : cheng
 * @date : 2020-10-18 19:26
 */
@RestController
@Slf4j
public class TestSentinelAdapterController {

    /**
     * okhttp实现流量控制
     * @return
     */
    @RequestMapping(value = "okhttpSentinel")
    public String okhttpSentinel(){
        SentinelOkHttpConfig config = new SentinelOkHttpConfig("okhttp:");
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(5, TimeUnit.SECONDS)
                //添加实现sentinel限流规则拦截器
                .addInterceptor(new SentinelOkHttpInterceptor(config))
                .build();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        String requestBody = "{\"name\":\"红楼梦\"}";

        Request request = new Request.Builder()
                .url("http://192.168.8.118:8200/monogodb-basic/book/listByBook")
                .post(RequestBody.create(mediaType, requestBody))
                .build();
        String body = null;
        try {
            body = client.newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(body);
        return body;
    }

    @PostConstruct
    public void initFlowQpsRule(){
        //创建限流规则集合
        List<FlowRule> rules = new ArrayList<>();
        //创建限流规则
        FlowRule flowRule = new FlowRule();
        //定义资源,表示Sentinel会对哪个资源生效
        flowRule.setResource("okhttp:POST:http://192.168.8.118:8200/monogodb-basic/book/listByBook");
        //定义限流规则类型,
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //定义qps每秒能通过的请求个数
        flowRule.setCount(2);

        rules.add(flowRule);
        //加载限流规则
        FlowRuleManager.loadRules(rules);
    }

}
