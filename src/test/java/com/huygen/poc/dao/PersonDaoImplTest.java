package com.huygen.poc.dao;

import com.huygen.poc.exception.PersonAppException;
import com.huygen.poc.model.Person;
import com.huygen.poc.support.PersonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import javax.persistence.NoResultException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ContextConfiguration(locations = "classpath:person-config.xml")
public class PersonDaoImplTest extends AbstractTransactionalTestNGSpringContextTests
{
    @Autowired
    private PersonDao personDao;

    public Person createPerson() throws PersonAppException
    {
        return PersonBuilder.aNewPerson().build();
    }

    @Test
    @Transactional
    public void testAddPerson() throws PersonAppException
    {
        //WHEN
        Person person = createPerson();
        personDao.addPerson(person);
        //THEN
        Person p = personDao.getPerson(person.getPersonId());
        assertThat(p, is(notNullValue()));
        assertThat(p.getPersonId(), is(person.getPersonId()));
    }

    @Test
    @Transactional
    public void testUpdatePerson() throws PersonAppException
    {
        //GIVEN
        Person person = createPerson();
        personDao.addPerson(person);
        //WHEN
        person.setFirstName("Barbie");
        personDao.updatePerson(person);
        //THEN
        Person p = personDao.getPerson(person.getPersonId());
        assertThat(p, is(notNullValue()));
        assertThat(p.getFirstName(), is(person.getFirstName()));
    }

    @Test(expectedExceptions = NoResultException.class)
    @Transactional
    public void testDeletePerson() throws PersonAppException
    {
        //GIVEN
        Person person = createPerson();
        personDao.addPerson(person);
        //WHEN
        personDao.deletePerson(person);
        //THEN
        personDao.getPerson(person.getPersonId());
    }

    @Test
    @Transactional
    public void testGetAllPersons() throws PersonAppException
    {
        //GIVEN
        Person person1 = createPerson();
        personDao.addPerson(person1);
        Person person2 = createPerson();
        personDao.addPerson(person2);
        //WHEN
        List<Person> personList = personDao.getAllPersons();
        //THEN
        assertThat(personList.size(), is(2));
        assertThat(personList, containsInAnyOrder(person1, person2));
    }

    @Test
    public void testGetPerson() throws PersonAppException
    {
        //GIVEN
        Person person = createPerson();
        personDao.addPerson(person);
        //WHEN
        Person personFetched = personDao.getPerson(person.getPersonId());
        //THEN
        assertThat(personFetched, is(person));
        assertThat(personFetched.getPersonId(), is(person.getPersonId()));
    }
}