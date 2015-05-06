package com.huygen.poc.dao;

import com.huygen.poc.config.PersonConfig;
import com.huygen.poc.model.Person;
import com.huygen.poc.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;

import static org.testng.Assert.*;

@ContextConfiguration(locations = "classpath:person-config.xml")
public class PersonDaoImplTest extends AbstractTestNGSpringContextTests
{
    @Autowired
    private PersonDaoImpl personDao;

    private Person person;

    public void setPersonDao(PersonDaoImpl personDao)
    {
        this.personDao = personDao;
    }

    @BeforeMethod
    public void setUp()
    {
        person = new Person();
        person.setPersonId(111);
        person.setFirstName("Merlin");
        person.setLastName("Jeniffer");

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        try
        {
            person.setDob(formatter.parse("01/07/1990 00:00:00"));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void testAddPerson() throws Exception
    {
        //GIVEN

        //WHEN
        personDao.addPerson(person);

        //THEN
        Person p = personDao.getPerson(person.getPersonId());
        assertNotNull(p);
        //assertEquals(person.getPersonId(), p.getPersonId());
    }

    /*@Test
    @Transactional
    public void testUpdatePerson() throws Exception
    {
        //GIVEN
        personDao.addPerson(person);
        person.setFirstName("Barbie");

        //WHEN
        personDao.updatePerson(person);

        //THEN
        Person p = personDao.getPerson(person.getPersonId());
        assertEquals(person.getFirstName(), p.getFirstName());

    }

    @Test
    @Transactional
    public void testDeletePerson() throws Exception
    {
        //GIVEN
        personDao.addPerson(person);

        //WHEN
        personDao.deletePerson(person);

        //THEN
        assertNull(personDao.getPerson(person.getPersonId()));
    }

    @Test
    @Transactional
    public void testGetAllPersons() throws Exception
    {
        //GIVEN
        personDao.addPerson(person);

        //WHEN
        personDao.getAllPersons();

        //THEN
        int size = personDao.getAllPersons().size();
        assertTrue(size == 0);
    }

    @Test
    public void testGetPerson() throws Exception
    {
        //GIVEN

        //WHEN

        //THEN
    }*/
}