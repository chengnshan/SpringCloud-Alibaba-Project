package com.cxp.storage.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cxp.storage.mapper.StorageDAO;
import com.cxp.storage.pojo.Storage;
import com.cxp.storage.service.StorageService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.api.transaction.Propagation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author cheng
 */
@Service
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDAO storageDAO;

    /**
     * 减库存,,
     *
     * @param commodityCode
     * @param count
     */
    @Override
    public void deduct(String commodityCode, int count) {
        System.out.println("StorageServiceImpl, 开始全局事务，XID = " + RootContext.getXID());
        /*if (commodityCode.equals("product-2")) {
            throw new RuntimeException("异常:模拟业务异常:Storage branch exception");
        }
*/
        QueryWrapper<Storage> wrapper = new QueryWrapper<>();
        wrapper.setEntity(new Storage().setCommodityCode(commodityCode));
        Storage storage = storageDAO.selectOne(wrapper);
        storage.setCount(storage.getCount() - count);

        storageDAO.updateById(storage);
    }
}
