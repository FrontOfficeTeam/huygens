package com.huygen.poc.util;

import com.huygen.poc.exception.PersonAppException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Suganya.Subramanian on 5/4/2015.
 */
public class DateUtil {

    public static Date convertToDate(String dateInString) throws PersonAppException{
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        try {
            return (formatter.parse(dateInString));
        } catch (ParseException exception){
            throw new PersonAppException("Error in parsing date.");
        }


    }
}
