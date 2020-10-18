package com.cxp.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cxp.order.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
