package com.huygen.poc.config;

import com.huygen.poc.dao.PersonDao;
import com.huygen.poc.dao.PersonDaoImpl;
import com.huygen.poc.service.PersonServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfig
{
    @Bean(name="personDao")
    public PersonDao getPersonDao(){
        return new PersonDaoImpl();
    }

    @Bean(name="personService")
    public PersonServiceImpl getPersonService() {
        PersonServiceImpl service = new PersonServiceImpl();
        service.setPersonDao(getPersonDao());
        return service;
    }
}
