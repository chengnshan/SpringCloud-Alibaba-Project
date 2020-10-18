package com.cxp.order.controller;

import com.cxp.order.pojo.Order;
import com.cxp.order.service.OrderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 减库存
     * @param order 订单信息
     * @return
     */
    @RequestMapping(path = "/creatOrder")
    public Boolean creatOrder(@RequestBody Order order) {
        orderService.placeOrder(order);
        return true;
    }

}
