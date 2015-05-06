package com.huygen.poc.model;

import com.huygen.poc.exception.PersonAppException;
import com.huygen.poc.util.DateUtil;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PERSON_DETAILS")
public class Person
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    @Column(name = "PERSON_ID")
    private Integer personId;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "DOB")
    private Date dob;
    @Column(name = "FROM_DATE")
    private Date fromDate;
    @Column(name = "TO_DATE")
    private Date toDate;

    private final Date FOREVER_DATE;

    public Person() throws PersonAppException
    {
        FOREVER_DATE = DateUtil.convertToDate("31/12/9999 00:00:00");
    }

    public void retireVersion()
    {
        setToDate(new Date());
    }

    public void addVersion()
    {
        setFromDate(new Date());
        setToDate(FOREVER_DATE);
    }

    public Person makeCopy() throws PersonAppException
    {
        Person p = new Person();
        p.setPersonId(getPersonId());
        p.setFirstName(getFirstName());
        p.setLastName(getLastName());
        p.setDob(getDob());
        p.setFromDate(getFromDate());
        p.setToDate(getToDate());
        return p;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Integer getPersonId()
    {
        return personId;
    }

    public void setPersonId(Integer personId)
    {
        this.personId = personId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public Date getDob()
    {
        return dob;
    }

    public void setDob(Date dob)
    {
        this.dob = dob;
    }

    public Date getFromDate()
    {
        return fromDate;
    }

    public void setFromDate(Date fromDate)
    {
        this.fromDate = fromDate;
    }

    public Date getToDate()
    {
        return toDate;
    }

    public void setToDate(Date toDate)
    {
        this.toDate = toDate;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        if (dob != null ? !dob.equals(person.dob) : person.dob != null) return false;
        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) return false;
        if (fromDate != null ? !fromDate.equals(person.fromDate) : person.fromDate != null) return false;
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null) return false;
        if (!personId.equals(person.personId)) return false;
        if (toDate != null ? !toDate.equals(person.toDate) : person.toDate != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + personId.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (dob != null ? dob.hashCode() : 0);
        result = 31 * result + (fromDate != null ? fromDate.hashCode() : 0);
        result = 31 * result + (toDate != null ? toDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "id=" + id +
                ", personId=" + personId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
