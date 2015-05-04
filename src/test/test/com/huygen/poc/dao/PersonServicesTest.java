package com.huygen.poc.dao;

import com.huygen.poc.config.PersonConfig;
import com.huygen.poc.model.Person;
import com.huygen.poc.service.PersonServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;

/*import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;*/
/*import static org.testng.Assert.*;*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:applicationContext.xml", classes = PersonConfig.class)
public class PersonServicesTest
{

    @Autowired
    private PersonServiceImpl personService;

   /* @DataProvider(name = "person")
    public Object[][] personData() throws ParseException {


        Person person1=new Person();

        person1.setPersonId(222);
        person1.setFirstName("Joy");
        person1.setLastName("Jeeva");

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        person1.setDob(formatter.parse("01/05/1990"));

        return new Object[][]{{person1}};
    }*/

    @Before
    public void setUp() throws Exception
    {
        Person person1 = new Person();
        person1.setPersonId(101);
        person1.setFirstName("Joy");
        person1.setLastName("Jeeva");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        person1.setDob(formatter.parse("01/05/1990"));
        personService.addPerson(person1);
    }


    @Transactional
    @Test
    public void testAddPerson() throws Exception
    {
        Person person1 = new Person();
        person1.setPersonId(102);
        person1.setFirstName("Suganya");
        person1.setLastName("Jeeva");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        person1.setDob(formatter.parse("01/05/1990"));
        personService.addPerson(person1);
        Person p = personService.getPerson(person1.getPersonId());
        System.out.println("Add" + p.toString());
        //  assertThat(person.getPersonId(), equals(p.getPersonId()));
    }

  /*  @Test(dataProvider = "person")
    @Transactional
    public void testUpdatePerson(Person personData) throws Exception {
        personData.setFirstName("Saranya");
        personService.updatePerson(personData);
       // System.out.println(" personData" + personData.toString());
        Person p1 = personService.getPerson(personData.getPersonId());
       // System.out.println("Person p" + p1.toString());
        assertEquals(personData.getPersonId(), p1.getPersonId());
     }

 /*   @Test(dataProvider = "person")
    @Transactional
    public void testDeletePerson(Person person) throws Exception {
        personService.deletePerson(person);
        assertNull(personService.getPerson(person.getPersonId()));
    }

    @Test(dataProvider = "person")
    public void testGetAllPersons(Person person) throws Exception {
        int size = personService.getAllPersons().size();
        assertTrue(size>0);
    }

    @Test(dataProvider = "person")
    public void testGetPerson(Person person) throws Exception {
        assertNotNull(personService.getPerson(person.getPersonId()));
    }*/
}