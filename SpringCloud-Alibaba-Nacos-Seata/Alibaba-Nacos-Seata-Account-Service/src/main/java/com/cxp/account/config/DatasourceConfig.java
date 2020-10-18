package com.cxp.account.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import io.seata.rm.datasource.DataSourceProxy;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * @author : cheng
 * @date : 2020-08-13 22:53
 */
//@Configuration
public class DatasourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.ds0")
    public DruidDataSource ds0(){
        return new DruidDataSource();
    }

    @Bean(value = "dataSourceProxy")
    @Primary
    public DataSourceProxy dataSourceProxy(DruidDataSource ds0){
        DataSourceProxy pds0 = new DataSourceProxy(ds0);
        return pds0;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSourceProxy dataSourceProxy,
                                                       MybatisPlusProperties mybatisProperties) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSourceProxy);

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            Resource[] mapperLocaltions = resolver.getResources(mybatisProperties.getMapperLocations()[0]);
            bean.setMapperLocations(mapperLocaltions);

            if (StringUtils.isNotBlank(mybatisProperties.getConfigLocation())) {
                Resource[] resources = resolver.getResources(mybatisProperties.getConfigLocation());
                bean.setConfigLocation(resources[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bean;
    }
}
