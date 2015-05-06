package com.huygen.poc;

import com.huygen.poc.model.Person;
import com.huygen.poc.service.PersonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;

public class Main
{
    public static void main(String... args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:person-config.xml");

        PersonService personService = (PersonService) applicationContext.getBean("personService");

        Person person = new Person();
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

        personService.addPerson(person);

        Person fetchedPerson = personService.getPerson(person.getPersonId());
        System.out.println(fetchedPerson.getPersonId());
    }
}
