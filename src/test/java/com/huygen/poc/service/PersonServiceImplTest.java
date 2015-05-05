package com.huygen.poc.service;

import com.huygen.poc.dao.PersonDao;
import com.huygen.poc.exception.PersonAppException;
import com.huygen.poc.model.Person;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

public class PersonServiceImplTest
{
    @Mock
    private PersonDao personDao;

    @InjectMocks
    private PersonServiceImpl personService;

    private Person person;

    @BeforeMethod
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeTest
    public void populate_person_object()
    {
        person = new Person();
        person.setPersonId(1);
        person.setFirstName("ABC");
        person.setLastName("XYZ");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try
        {
            person.setDob(formatter.parse("01/05/1990"));
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void should_add_person() throws PersonAppException
    {
        //GIVEN

        //WHEN
        personService.addPerson(person);

        //THEN
        verify(personDao).addPerson(person);
    }

    @Test
    public void should_update_person() throws PersonAppException
    {
        //GIVEN

        //WHEN
        personService.updatePerson(person);

        //THEN
        verify(personDao).updatePerson(person);
    }

    @Test
    public void should_delete_person() throws PersonAppException
    {
        //GIVEN

        //WHEN
        personService.deletePerson(person);

        //THEN
        verify(personDao).deletePerson(person);
    }

    @Test
    public void should_get_person() throws PersonAppException
    {
        //GIVEN
        doReturn(person).when(personDao).getPerson(anyInt());

        //WHEN
        Person returnedPerson = personService.getPerson(person.getPersonId());

        //THEN
        verify(personDao).getPerson(person.getPersonId());
        assertEquals(returnedPerson.getPersonId(), person.getPersonId());
    }

    @Test
    public void should_get_all_person() throws PersonAppException
    {
        //GIVEN
        List<Person> personList = new ArrayList<Person>();
        personList.add(person);
        person.setPersonId(101);
        person.setFirstName("Joy");
        personList.add(person);
        doReturn(personList).when(personDao).getAllPersons();

        //WHEN
        List<Person> returnedPersonList = personService.getAllPersons();

        //THEN
        verify(personDao).getAllPersons();
        assertEquals(personList, returnedPersonList);
    }

    @Test
    public void should_not_add_person() throws PersonAppException
    {
        //GIVEN
        Person person1 = null;

        //WHEN
        personService.addPerson(person1);


        //THEN
        verify(personDao, never()).addPerson(person1);
    }

    @Test
    public void should_not_update_person() throws PersonAppException
    {
        //GIVEN
        Person person1 = null;

        //WHEN
        personService.updatePerson(person1);

        //THEN
        verify(personDao, never()).updatePerson(person1);
    }

    @Test
    public void should_not_delete_person() throws PersonAppException
    {
        //GIVEN
        Person person1 = null;

        //WHEN
        personService.deletePerson(person1);

        //THEN
        verify(personDao, never()).deletePerson(person1);
    }
}
