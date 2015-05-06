package com.huygen.poc.support;

import com.huygen.poc.model.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PersonBuilder
{
    private Person person;

    public PersonBuilder()
    {
        this.person = createWithDefaults();
    }

    private Person createWithDefaults()
    {
        Person person = new Person();
        person.setPersonId(1);
        person.setFirstName("Tim");
        person.setLastName("Duncan");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try
        {
            person.setDob(formatter.parse("01/05/1990"));
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        return person;
    }

    public static PersonBuilder aPerson()
    {
        return new PersonBuilder();
    }

    public PersonBuilder withFirstName(String firstName)
    {
        this.person.setFirstName(firstName);
        return this;
    }

    public Person build()
    {
        return person;
    }
}
