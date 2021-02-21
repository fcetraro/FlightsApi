package com.ml.FlightsApi.util;


import com.ml.FlightsApi.exception.implementation.NotValidDateException;
import com.ml.FlightsApi.exception.implementation.NotValidEmailException;
import com.ml.FlightsApi.exception.implementation.NotValidPaymentMethodException;
import com.ml.FlightsApi.exception.implementation.ProvinceNotFoundException;
import com.ml.FlightsApi.model.FlightDTO;
import com.ml.FlightsApi.model.PaymentMethodDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ml.FlightsApi.util.DateFormat.DATE_FORMAT;

public class Validator {
    private static final String emailRegex = "^(.+)@(.+)$";
    private static boolean isCityValid(String destination, List<FlightDTO> flights){
        for (FlightDTO flight:flights) {
            if(flight.getDestination().equals(destination) || flight.getOrigin().equals(destination)) return true;
        }
        return false;
    }
    public static void validateCity(String destination, List<FlightDTO> flights){
        if(!isCityValid(destination, flights)){
            throw new ProvinceNotFoundException(destination,new Exception("Province not found"));
        }
    }
    private static void validateRangeDates(Date dateFrom, Date dateTo) {
        if(dateFrom.after(dateTo)){
            throw new NotValidDateException(new Exception());
        }
    }
    public static void validateDatesString(String dateFromString, String dateToString) {
        try{
            Date dateFrom = new SimpleDateFormat(DATE_FORMAT).parse(dateFromString);
            Date dateTo = new SimpleDateFormat(DATE_FORMAT).parse(dateToString);
            validateRangeDates(dateFrom, dateTo);
        } catch (ParseException e) {
            throw new NotValidDateException(e);
        }
    }
    public static void validateEmail(String email){
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()) throw new NotValidEmailException(new Exception(email + " is not a valid!"));
    }

    private static void validateCreditCard(PaymentMethodDTO method){
        if(method.getDues()<=0) {
            throw new NotValidPaymentMethodException(new Exception("Se ha ingresado una cantidad de " +
                    "cuotas igual o menor a 0."));
        }
    }
    private static void validateDebitCard(PaymentMethodDTO method){
        if(method.getDues()!=1) {
            throw new NotValidPaymentMethodException(new Exception("Se ha ingresado una cantidad de" +
                    " cuotas diferente a 1."));
        }
    }
    public static void validatePaymentMethod(PaymentMethodDTO method){
        switch (method.getType().toUpperCase(Locale.ROOT))
        {
            case "CREDIT":  validateCreditCard(method);
                break;
            case "DEBIT":  validateDebitCard(method);
                break;
            default:
                throw new NotValidPaymentMethodException(new Exception(method.getType() + " is not a valid " +
                        "payment method"));
        }
    }
}
