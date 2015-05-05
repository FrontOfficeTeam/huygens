package com.huygen.poc.exception;

public class PersonAppException extends Exception
{
    String message;

    public PersonAppException(String message)
    {
        super(message);
    }

    @Override
    public String toString()
    {
        return "PersonAppException{" +
                "message='" + message + '\'' +
                '}';
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
