package com.huygen.poc.dao;
import com.huygen.poc.exception.PersonAppException;
import com.huygen.poc.model.Person;

import java.util.List;

/**
 * Created by suganya.subramanian on 4/21/2015.
 */
public interface PersonDao {

    public void addPerson(Person person) throws PersonAppException;
    public void updatePerson(Person person) throws PersonAppException;
    public void deletePerson(Person person) throws PersonAppException;
    public List<Person> getAllPersons() throws PersonAppException;
    public Person getPerson(int personId) throws PersonAppException;
}