package com.huygen.poc.config;

import com.huygen.poc.dao.PersonDao;
import com.huygen.poc.dao.PersonDaoImpl;
import com.huygen.poc.service.PersonServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.SharedEntityManagerBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class PersonConfig
{
    @Bean
    public DataSource datasource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl("jdbc:hsqldb:mem:person.sql");
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
        localContainerEntityManagerFactoryBean.setPersistenceXmlLocation("classpath:persistence.xml");
        localContainerEntityManagerFactoryBean.setPersistenceUnitName("corePersistenceUnit");
        localContainerEntityManagerFactoryBean.setDataSource(datasource());
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        localContainerEntityManagerFactoryBean.afterPropertiesSet();
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.hbm2ddl.auto","create-drop");
        localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties);

        return localContainerEntityManagerFactoryBean.getObject();
    }

    /*@Bean
    public EntityManager entityManager()
    {
        SharedEntityManagerBean entityManager = new SharedEntityManagerBean();
        entityManager.setEntityManagerFactory(entityManagerFactory());
        entityManager.afterPropertiesSet();
        return entityManager.getObject();
    }*/

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
        personDao.setEntityManager(entityManagerFactory().createEntityManager());

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
