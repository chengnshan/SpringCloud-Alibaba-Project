package com.cxp.storage.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cxp.storage.mapper.StorageDAO;
import com.cxp.storage.pojo.Storage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author cheng
 */

public interface StorageService {

    /**
     * 减库存,,
     *
     * @param commodityCode
     * @param count
     */
    public void deduct(String commodityCode, int count) ;
}
