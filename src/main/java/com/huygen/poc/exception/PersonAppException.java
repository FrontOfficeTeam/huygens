package com.huygen.poc.exception;

public class PersonAppException extends Exception
{
    public PersonAppException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
