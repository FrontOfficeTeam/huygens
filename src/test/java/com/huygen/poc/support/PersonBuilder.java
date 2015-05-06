package com.huygen.poc.support;

import com.huygen.poc.exception.PersonAppException;
import com.huygen.poc.model.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PersonBuilder
{
    private Person person;

    private PersonBuilder(Person person)
    {
        this.person = person;
    }

    public static PersonBuilder aNewPerson() throws PersonAppException
    {
        PersonBuilder builder = new PersonBuilder(buildWithDefaults());
        return builder;
    }

    public PersonBuilder withFirstName(String firstName)
    {
        this.person.setFirstName(firstName);
        return this;
    }

    private static Person buildWithDefaults() throws PersonAppException
    {
        Person p = new Person();
        p.setPersonId(1);
        p.setFirstName("ABC");
        p.setLastName("XYZ");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try
        {
            p.setDob(formatter.parse("01/05/1990"));
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        return p;
    }

    public Person build()
    {
        return this.person;
    }
}
