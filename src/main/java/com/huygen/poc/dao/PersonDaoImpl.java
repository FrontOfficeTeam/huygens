package com.huygen.poc.dao;

import com.huygen.poc.exception.PersonAppException;
import com.huygen.poc.model.Person;
import com.huygen.poc.util.DateUtil;
import org.springframework.dao.DataAccessException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PersonDaoImpl implements PersonDao
{
    private EntityManager entityManager;
    private final String FOREVER_DATE = "31/12/9999 00:00:00";
    private final String PERSON_ID = "personId";
    private final String TO_DATE = "toDate";


    public void setEntityManager(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    public void addPerson(Person person) throws PersonAppException
    {
        try
        {
            Objects.requireNonNull(person);
            person.setFromDate(new Date());
            person.setToDate(DateUtil.convertToDate(FOREVER_DATE));
            entityManager.persist(person);

        } catch (DataAccessException exception)
        {
            throw new PersonAppException("Error in inserting person details", exception);
        } catch (Exception e)
        {
            throw new PersonAppException("Error in insert person", e);
        }
    }

    public void updatePerson(Person person) throws PersonAppException
    {
        try
        {
            Objects.requireNonNull(person);
            Person personFromDataBase = getPerson(person.getPersonId());
            personFromDataBase.setToDate(new Date());
            entityManager.merge(personFromDataBase);

            person.setToDate(DateUtil.convertToDate(FOREVER_DATE));
            addPerson(person);

        } catch (DataAccessException exception)
        {
            throw new PersonAppException("Error in updating person details", exception);
        } catch (Exception e)
        {
            throw new PersonAppException("Error in update person", e);
        }
    }

    public void deletePerson(Person person) throws PersonAppException
    {
        try
        {
            Objects.requireNonNull(person);
            Person personFromDataBase = getPerson(person.getPersonId());
            personFromDataBase.setToDate(new Date());
            entityManager.merge(personFromDataBase);

        } catch (DataAccessException exception)
        {
            throw new PersonAppException("Error in deleting person details", exception);
        } catch (Exception e)
        {
            throw new PersonAppException("Error in delete person", e);
        }

    }

    public List<Person> getAllPersons() throws PersonAppException
    {
        try
        {
            Query query = entityManager.createQuery("select p from Person p where p.toDate = :param1");
            query.setParameter("param1", DateUtil.convertToDate(FOREVER_DATE));
            if (null != query.getResultList())
            {
                List<Person> persons = query.getResultList();
                return persons;
            } else
            {
                return Collections.EMPTY_LIST;
            }
        } catch (DataAccessException exception)
        {
            throw new PersonAppException("Error in retrieving person details", exception);
        } catch (Exception e)
        {
            throw new PersonAppException("Error in retrieve person", e);
        }

    }


    public Person getPerson(int personId) throws PersonAppException
    {
        try
        {
            //Query query = entityManager.createQuery("select p from Person p where p.personId = :param1 and " + " p.toDate = :param2");
            Query query = entityManager.createQuery("select p from Person p where p.personId = :param1");
            query.setParameter("param1", personId);
            //query.setParameter("param2", DateUtil.convertToDate(FOREVER_DATE));

            return (Person) query.getSingleResult();

            /*if (null != query.getResultList() && !query.getResultList().isEmpty())
            {
                System.out.println("GetPErson" + (Person) query.getSingleResult());
            }
            return (null != query.getResultList() && !query.getResultList().isEmpty()) ?
                    ((Person) query.getSingleResult()) : null;*/

        } catch (DataAccessException exception)
        {
            throw new PersonAppException("Error in retrieving person details", exception);
        } catch (Exception e)
        {
            throw new PersonAppException("Error in retrieve person", e);
        }
    }
}
