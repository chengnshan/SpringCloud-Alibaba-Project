package com.cxp.controller;

import com.alibaba.csp.sentinel.AsyncEntry;
import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphO;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.cxp.service.TestSentinelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : cheng
 * @date : 2020-10-18 19:26
 */
@RestController
@Slf4j
public class TestFlowRuleController {

    @Autowired
    private TestSentinelService testSentinelService;

    @Value("#{'${auth.filter.exclude-urls}'.split(',')}")
    private List<String> authUrls;

    @RequestMapping(value = "helloSentinel")
    public String helloSentinel(){
        //使用限流规则
        try(Entry entry = SphU.entry("helloSentinel")) {
            return "helloSentinel";
        } catch (BlockException e) {
            e.printStackTrace();
            return "系统繁忙,请稍候!";
        }
    }

    @PostConstruct
    public void initFlowQpsRule(){
        //创建限流规则集合
        List<FlowRule> rules = new ArrayList<>();
        //创建限流规则
        FlowRule flowRule = new FlowRule();
        //定义资源,表示Sentinel会对哪个资源生效
        flowRule.setResource("helloSentinel");
        //定义限流规则类型,
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //定义qps每秒能通过的请求个数
        flowRule.setCount(2);

        rules.add(flowRule);
        //加载限流规则
        FlowRuleManager.loadRules(rules);
    }

    /**
     * 返回布尔值方式定义资源
     * @return
     */
    @RequestMapping(value = "booleanHelloSentinel")
    public boolean booleanHelloSentinel(){
        //使用限流规则
        //限流入口
        if (SphO.entry("booleanHelloSentinel")){
            try{
                //被保护的业务逻辑
                log.info("booleanHelloSentinel");
                return true;
            } finally {
                SphO.exit();
            }
        }else {
            // 资源访问阻止，被限流或被降级
            // 进行相应的处理操作
            log.info("系统繁忙,请稍候!");
            return false;
        }
    }

    /**
     * 使用注解的方式
     * @return
     */
    @SentinelResource(value = "sentinel_annotation", blockHandler = "blockHandler")
    @RequestMapping(value = "annotationSentinel")
    public String annotationSentinel(){

        return "annotationSentinel";
    }

    /**
     * 被降级或限流的处理函数
     * @param e
     * @return
     */
    public String blockHandler(BlockException e){
        e.printStackTrace();
        return "系统繁忙,请稍候!";
    }

    /**
     * 异步调用的方式
     */
    @GetMapping(value = "asyncSentinel")
    public void asyncSentinel(){
        AsyncEntry asyncEntry = null;
        try {
            asyncEntry = SphU.asyncEntry("sentinel_async");
            testSentinelService.asyncSentinel();
        } catch (BlockException e) {
            e.printStackTrace();
            System.out.println("系统繁忙,请稍候!");
        }finally {
            if (asyncEntry != null){
                asyncEntry.exit();
            }
        }
    }
}
