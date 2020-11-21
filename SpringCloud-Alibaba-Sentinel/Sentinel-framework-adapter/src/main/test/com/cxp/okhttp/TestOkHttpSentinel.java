package com.cxp.okhttp;

import com.alibaba.csp.sentinel.adapter.okhttp.SentinelOkHttpConfig;
import com.alibaba.csp.sentinel.adapter.okhttp.SentinelOkHttpInterceptor;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author : cheng
 * @date : 2020-10-24 14:58
 */
public class TestOkHttpSentinel {

    @Before
    public void before(){
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

    @Test
    public void test1() throws IOException {
        SentinelOkHttpConfig config = new SentinelOkHttpConfig("okhttp:");
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(new SentinelOkHttpInterceptor(config))
                .build();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        String requestBody = "{\"name\":\"红楼梦\"}";

        Request request = new Request.Builder()
                .url("http://192.168.8.118:8200/monogodb-basic/book/listByBook")
                .post(RequestBody.create(mediaType, requestBody))
                .build();
        String body = client.newCall(request).execute().body().string();
        System.out.println(body);
    }
}
