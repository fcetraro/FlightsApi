package com.ml.FlightsApi.filter;

import com.ml.FlightsApi.model.FlightDTO;

import java.util.function.Predicate;

public abstract class FlightFilter {
    public abstract Predicate<FlightDTO> getPredicate();
    public abstract String getFilterName();
    public boolean matchFilterName(String filterName){
        return getFilterName().equals(filterName);
    }

    public abstract void setValue(String value);
}
