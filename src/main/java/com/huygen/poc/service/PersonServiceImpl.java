package com.huygen.poc.service;

import com.huygen.poc.dao.PersonDao;
import com.huygen.poc.exception.PersonAppException;
import com.huygen.poc.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class PersonServiceImpl implements PersonService
{
    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    private PersonDao personDao;

    public void setPersonDao(PersonDao personDao)
    {
        this.personDao = personDao;
    }

    @Override
    @Transactional
    public void addPerson(Person person)
    {
        Objects.requireNonNull(person);
        personDao.addPerson(person);
    }

    @Override
    @Transactional
    public void updatePerson(Person person) throws PersonAppException
    {
        Objects.requireNonNull(person);
        personDao.updatePerson(person);
    }

    @Override
    @Transactional
    public void deletePerson(Person person)
    {
        Objects.requireNonNull(person);
        personDao.deletePerson(person);
    }

    @Override
    @Transactional
    public List<Person> getAllPersons()
    {
        return personDao.getAllPersons();
    }

    @Override
    @Transactional
    public Person getPerson(int personId)
    {
        return personDao.getPerson(personId);
    }
}
