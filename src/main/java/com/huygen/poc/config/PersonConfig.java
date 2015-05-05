package com.huygen.poc.config;

import com.huygen.poc.dao.PersonDao;
import com.huygen.poc.dao.PersonDaoImpl;
import com.huygen.poc.service.PersonServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.SharedEntityManagerBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class PersonConfig
{
    @Bean
    public DataSource datasource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl("jdbc:hsqldb:mem:.");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter()
    {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.HSQL);
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.HSQLDialect");

        return jpaVendorAdapter;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory()
    {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        //localContainerEntityManagerFactoryBean.setPackagesToScan("com.huygen.poc.model");
        localContainerEntityManagerFactoryBean.setDataSource(datasource());
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        jpaProperties.setProperty("hibernate.show_sql", "true");
        jpaProperties.setProperty("hibernate.format_sql", "false");
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        jpaProperties.setProperty("hibernate.autocommit", "true");

        localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties);

        localContainerEntityManagerFactoryBean.afterPropertiesSet();
        return localContainerEntityManagerFactoryBean.getObject();
    }

    @Bean
    public EntityManager entityManager()
    {
        SharedEntityManagerBean entityManager = new SharedEntityManagerBean();
        entityManager.setEntityManagerFactory(entityManagerFactory());
        entityManager.afterPropertiesSet();
        return entityManager.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager()
    {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory());

        return transactionManager;
    }

    @Bean
    public PersonDao personDao()
    {
        PersonDaoImpl personDao = new PersonDaoImpl();
        //personDao.setEntityManager(entityManagerFactory().createEntityManager());
        personDao.setEntityManager(entityManager());

        return personDao;
    }

    @Bean
    public PersonServiceImpl personService()
    {
        PersonServiceImpl service = new PersonServiceImpl();
        service.setPersonDao(personDao());
        return service;
    }
}
