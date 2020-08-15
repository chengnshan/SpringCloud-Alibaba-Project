package com.cxp.storage.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cxp.storage.pojo.Storage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author cheng
 */
@Mapper
@Repository
public interface StorageDAO extends BaseMapper<Storage> {

}
