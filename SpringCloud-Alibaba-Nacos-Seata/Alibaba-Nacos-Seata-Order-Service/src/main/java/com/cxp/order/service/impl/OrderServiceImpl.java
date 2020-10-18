package com.cxp.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxp.common.response.ObjectResponse;
import com.cxp.order.feignclient.AccountFeinClient;
import com.cxp.order.feignclient.StorageFeignClient;
import com.cxp.order.mapper.OrderMapper;
import com.cxp.order.pojo.Order;
import com.cxp.order.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.api.transaction.Propagation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author cheng
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private StorageFeignClient storageFeignClient;
    @Resource
    private AccountFeinClient accountFeinClient;
    @Resource
    private OrderMapper orderMapper;


    /**
     * 下单：创建订单、减库存，涉及到两个服务
     *
     * @param order
     */
    @Override
    @GlobalTransactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void placeOrder(Order order) {
        System.out.println("开始全局事务，XID = " + RootContext.getXID());
        save(order);
        Boolean deduct = storageFeignClient.deduct(order.getCommodityCode(), order.getCount());
        ObjectResponse response = accountFeinClient.decAccount(order.getUserId(), order.getMoney());
        log.info("请求库存服务结果: {}, 请求帐户服务结果: {}",deduct,response);
        if (order.getId() % 2 == 0){
            int i = 10 /0 ;
        }
    }

}
