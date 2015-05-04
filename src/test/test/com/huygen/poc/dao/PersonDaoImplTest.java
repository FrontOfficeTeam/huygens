package com.huygen.poc.dao;

import com.huygen.poc.exception.PersonAppException;
import com.huygen.poc.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.testng.Assert.*;

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PersonDaoImplTest extends AbstractTestNGSpringContextTests
{


    @Autowired
    private PersonDaoImpl personDao = null;

    @DataProvider(name = "person")
    public Object[][] personData() throws ParseException
    {
        Person person1 = new Person();
        person1.setPersonId(333);
        person1.setFirstName("Hema");
        person1.setLastName("Latha");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        person1.setDob(formatter.parse("01/05/1990"));

        return new Object[][]{{person1}};
    }

    @BeforeMethod
    public void setUp() throws Exception
    {
        Person person1 = new Person();
        person1.setPersonId(101);
        person1.setFirstName("Joy");
        person1.setLastName("Jeeva");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        person1.setDob(formatter.parse("01/05/1990"));
        personDao.addPerson(person1);
    }

    @Test(dataProvider = "person")
    @Transactional
    public void testAddPerson(Person person) throws Exception
    {
        personDao.addPerson(person);
        Person p = personDao.getPerson(person.getPersonId());
        assertEquals(person.getPersonId(), p.getPersonId());
    }

    @Test(dataProvider = "person")
    @Transactional
    public void testUpdatePerson(Person personData) throws Exception
    {
        personData.setFirstName("Saranya");
        personDao.updatePerson(personData);

        Person p = personDao.getPerson(personData.getPersonId());
        assertEquals(personData.getPersonId(), p.getPersonId());
    }

    @Test(dataProvider = "person")
    @Transactional
    public void testDeletePerson(Person person) throws Exception
    {
        personDao.deletePerson(person);
        assertNull(personDao.getPerson(person.getPersonId()));
    }

    @Test(dataProvider = "person")
    @Transactional
    public void testGetAllPersons(Person person) throws Exception
    {
        int size = personDao.getAllPersons().size();
        assertTrue(size > 0);
    }

    @Test(dataProvider = "person")
    @Transactional
    public void testGetPerson(Person person) throws Exception
    {
        assertNotNull(personDao.getPerson(person.getPersonId()));
    }
}