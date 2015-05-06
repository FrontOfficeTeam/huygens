package com.huygen.poc.service;

import com.huygen.poc.dao.PersonDao;
import com.huygen.poc.exception.PersonAppException;
import com.huygen.poc.model.Person;
import com.huygen.poc.support.PersonBuilder;
import org.mockito.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class PersonServiceImplTest
{
    @Mock
    private PersonDao personDao;

    @InjectMocks
    private PersonServiceImpl personService;

    @Captor
    private ArgumentCaptor<Person> personCaptor;

    @Captor
    private ArgumentCaptor<Integer> personIdCaptor;

    @BeforeMethod
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
    }

    public Person createPerson() throws PersonAppException
    {
        return PersonBuilder.aNewPerson().build();
    }

    @Test
    public void add_person_delegates_to_dao() throws PersonAppException
    {
        //WHEN
        Person person = createPerson();
        personService.addPerson(person);
        //THEN
        verify(personDao).addPerson(personCaptor.capture());
        Person thePersonThatWasSaved = personCaptor.getValue();
        assertThat(thePersonThatWasSaved, is(person));
        assertThat(thePersonThatWasSaved.getPersonId(), is(person.getPersonId()));
        assertThat(thePersonThatWasSaved.getFirstName(), is(person.getFirstName()));
    }

    @Test
    public void update_person_delegates_to_dao() throws PersonAppException
    {
        //WHEN
        Person person = PersonBuilder.aNewPerson().withFirstName("Traf").build();
        personService.updatePerson(person);
        //THEN
        verify(personDao).updatePerson(personCaptor.capture());
        Person thePersonThatWasUpdated = personCaptor.getValue();
        assertThat(thePersonThatWasUpdated, is(person));
        assertThat(thePersonThatWasUpdated.getPersonId(), is(person.getPersonId()));
    }

    @Test
    public void delete_person_delegates_to_dao() throws PersonAppException
    {
        //WHEN
        Person person = createPerson();
        personService.deletePerson(person);

        //THEN
        verify(personDao).deletePerson(personCaptor.capture());
        Person thePersonThatWasDeleted = personCaptor.getValue();
        assertThat(thePersonThatWasDeleted, is(person));
        assertThat(thePersonThatWasDeleted.getPersonId(), is(person.getPersonId()));
    }

    @Test
    public void get_person_delegates_to_dao() throws PersonAppException
    {
        //GIVEN
        Person person = createPerson();
        when(personDao.getPerson(anyInt())).thenReturn(person);
        //WHEN
        Person personFetched = personService.getPerson(person.getPersonId());
        //THEN
        verify(personDao).getPerson(personIdCaptor.capture());
        int thePersonIdThatWasPassed = personIdCaptor.getValue();
        assertThat(personFetched, is(person));
        assertThat(thePersonIdThatWasPassed, is(personFetched.getPersonId()));
    }

    @Test
    public void get_all_person_delegates_to_dao() throws PersonAppException
    {
        //GIVEN
        List<Person> personList = new ArrayList<Person>();
        Person firstPerson = PersonBuilder.aNewPerson().build();
        personList.add(firstPerson);
        Person secondPerson = PersonBuilder.aNewPerson().build();
        personList.add(secondPerson);
        when(personDao.getAllPersons()).thenReturn(personList);
        //WHEN
        List<Person> people = personService.getAllPersons();
        //THEN
        verify(personDao).getAllPersons();
        assertThat(people, containsInAnyOrder(firstPerson, secondPerson));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void should_not_add_person()
    {
        //WHEN
        personService.addPerson(null);
        //THEN
        verify(personDao, never()).addPerson(any(Person.class));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void should_not_update_person() throws PersonAppException
    {
        //WHEN
        personService.updatePerson(null);
        //THEN
        verify(personDao, never()).updatePerson(any(Person.class));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void should_not_delete_person() throws PersonAppException
    {
        //WHEN
        personService.deletePerson(null);
        //THEN
        verify(personDao, never()).deletePerson(any(Person.class));
    }
}
