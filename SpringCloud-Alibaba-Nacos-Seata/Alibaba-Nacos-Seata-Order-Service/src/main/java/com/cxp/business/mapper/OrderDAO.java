package com.cxp.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cxp.business.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderDAO extends BaseMapper<Order> {

}
