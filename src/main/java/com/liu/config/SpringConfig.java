package com.liu.config;

/*
 * @author  LiuHuiPeng
 * @date    2022/3/24 10:58
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

//Spring 相关的配置类
@Configuration
@Import({MyBatisConfig.class,JdbcConfig.class})
@ComponentScan("com.liu.service")
@EnableTransactionManagement
public class SpringConfig {
    @Bean("transactionManager")
    public DataSourceTransactionManager getDataSourceTransactionManager(@Autowired DataSource dataSource){
        DataSourceTransactionManager dstm = new DataSourceTransactionManager();
        dstm.setDataSource(dataSource);
        return dstm;
    }
}
