package com.ml.FlightsApi.filter.concret;


import com.ml.FlightsApi.exception.implementation.NotValidDateException;
import com.ml.FlightsApi.filter.FlightFilter;
import com.ml.FlightsApi.model.FlightDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Predicate;

import static com.ml.FlightsApi.util.DateFormat.DATE_FORMAT;


public class DateTo extends FlightFilter {
    Date dateTo;

    @Override
    public Predicate<FlightDTO> getPredicate() {
        return w -> w.getDateTo().after(dateTo)|| w.getDateTo().equals(dateTo);
    }

    @Override
    public String getFilterName() {
        return "dateTo";
    }

    @Override
    public void setValue(String value){
        try {
            dateTo = new SimpleDateFormat(DATE_FORMAT).parse(value);
        } catch (ParseException e){
            throw new NotValidDateException(e);
        }
    }
}
