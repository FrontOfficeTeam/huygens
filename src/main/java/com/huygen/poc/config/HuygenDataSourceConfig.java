package com.huygen.poc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


/**
 * Created by Suganya.Subramanian on 5/4/2015.
 */
@Configuration
public class HuygenDataSourceConfig
{

    @Bean
    public DataSource datasource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl("jdbc:hsqldb:mem:.");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

}
