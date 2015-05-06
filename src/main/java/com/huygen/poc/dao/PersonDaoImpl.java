package com.huygen.poc.dao;

import com.huygen.poc.exception.PersonAppException;
import com.huygen.poc.model.Person;
import com.huygen.poc.util.DateUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository
public class PersonDaoImpl implements PersonDao
{
    @PersistenceContext
    private EntityManager entityManager;

    private final Date FOREVER_DATE;

    public PersonDaoImpl() throws PersonAppException
    {
        FOREVER_DATE = DateUtil.convertToDate("31/12/9999 00:00:00");
    }

    public void addPerson(Person person)
    {
        Objects.requireNonNull(person);
        person.addVersion();
        entityManager.persist(person);
    }

    public void updatePerson(Person person) throws PersonAppException
    {
        Objects.requireNonNull(person);
        Person personFromDataBase = getPerson(person.getPersonId());
        person.retireVersion();
        entityManager.merge(personFromDataBase);

        Person copyOfPerson = person.makeCopy();
        copyOfPerson.addVersion();
        addPerson(person);
    }

    public void deletePerson(Person person)
    {
        Objects.requireNonNull(person);
        Person personFromDataBase = getPerson(person.getPersonId());
        personFromDataBase.retireVersion();
        entityManager.merge(personFromDataBase);
    }

    public List<Person> getAllPersons()
    {
        Query query = entityManager.createQuery("from Person where toDate = :toDate");
        query.setParameter("toDate", FOREVER_DATE);
        List persons = query.getResultList();
        if (null != persons)
        {
            return persons;
        } else
        {
            return Collections.EMPTY_LIST;
        }
    }

    public Person getPerson(int personId)
    {
        Query query = entityManager.createQuery("from Person where personId = :personId and toDate = :date");
        query.setParameter("personId", personId);
        query.setParameter("date", FOREVER_DATE);

        return (Person) query.getSingleResult();
    }
}
