package com.huygen.poc.service;

import com.huygen.poc.dao.PersonDao;
import com.huygen.poc.exception.PersonAppException;
import com.huygen.poc.model.Person;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
}