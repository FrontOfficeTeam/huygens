package com.huygen.poc.service;

import com.huygen.poc.model.Person;

import java.util.List;

public interface PersonService
{
    public void addPerson(Person person);

    public void updatePerson(Person person);

    public void deletePerson(Person person);

    public List<Person> getAllPersons();

    public Person getPerson(int personId);
}
