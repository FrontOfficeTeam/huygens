package com.huygen.poc.service;

import com.huygen.poc.dao.PersonDao;
import com.huygen.poc.exception.PersonAppException;
import com.huygen.poc.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService
{
    //@Autowired
    private PersonDao personDao;
    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    public void setPersonDao(PersonDao personDao)
    {
        this.personDao = personDao;
    }

    @Override
    @Transactional
    public void addPerson(Person person)
    {
        if (null != person)
        {
            try
            {
                System.out.println("SERVICE " + person.toString());
                personDao.addPerson(person);
            } catch (PersonAppException exception)
            {
                logger.debug(exception.getMessage());
            }
        } else
        {
            logger.debug("Person object is null");
        }

    }

    @Override
    @Transactional
    public void updatePerson(Person person)
    {
        if (null != person)
        {
            try
            {
                personDao.updatePerson(person);
            } catch (PersonAppException exception)
            {
                logger.debug(exception.getMessage());
            }
        } else
        {
            logger.debug("Person object is null");
        }
    }

    @Override
    @Transactional
    public void deletePerson(Person person)
    {
        if (null != person)
        {
            try
            {
                personDao.deletePerson(person);
            } catch (PersonAppException exception)
            {
                logger.debug(exception.getMessage());
            } catch (Exception e)
            {
                logger.debug("com.trafigura.poc.exception while adding a person record in db");
            }
        } else
        {
            logger.debug("Person object is null");
        }
    }

    @Override
    @Transactional
    public List<Person> getAllPersons()
    {
        try
        {
            return personDao.getAllPersons();
        } catch (PersonAppException exception)
        {
            logger.debug(exception.getMessage());
        }
        return null;
    }

    @Override
    @Transactional
    public Person getPerson(int personId)
    {
        try
        {
            return personDao.getPerson(personId);
        } catch (PersonAppException exception)
        {
            logger.debug(exception.getMessage());
        }
        return null;
    }
}
