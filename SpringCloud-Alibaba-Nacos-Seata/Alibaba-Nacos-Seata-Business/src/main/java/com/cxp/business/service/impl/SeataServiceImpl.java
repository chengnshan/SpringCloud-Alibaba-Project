package com.cxp.business.service.impl;

import com.cxp.business.service.SeataService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * @author : cheng
 * @date : 2020-08-13 23:16
 */
@Service
public class SeataServiceImpl implements SeataService, InitializingBean {

    @Autowired
    private DataSource druidDataSource;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(druidDataSource);
    }
}
