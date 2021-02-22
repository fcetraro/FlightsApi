package com.ml.FlightsApi.filter.concret;

import com.ml.FlightsApi.exception.implementation.NotValidDateException;
import com.ml.FlightsApi.filter.FlightFilter;
import com.ml.FlightsApi.model.FlightDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Predicate;

import static com.ml.FlightsApi.util.DateFormat.DATE_FORMAT;


public class DateFrom extends FlightFilter {
    Date dateFrom;

    @Override
    public Predicate<FlightDTO> getPredicate() {
        return w -> w.getDateFrom().equals(dateFrom);
    }

    @Override
    public String getFilterName() {
        return "dateFrom";
    }

    @Override
    public void setValue(String value){
        try {
            dateFrom = new SimpleDateFormat(DATE_FORMAT).parse(value);
        } catch (ParseException e){
            throw new NotValidDateException(e);
        }
    }
}
