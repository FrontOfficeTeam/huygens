package com.huygen.poc.dao;

import com.huygen.poc.exception.PersonAppException;
import com.huygen.poc.model.Person;

import java.util.List;

public interface PersonDao
{
    public void addPerson(Person person);

    public void updatePerson(Person person) throws PersonAppException;

    public void deletePerson(Person person);

    public List<Person> getAllPersons();

    public Person getPerson(int personId);
}
