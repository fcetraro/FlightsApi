package com.ml.FlightsApi.filter.concret;

import com.ml.FlightsApi.filter.FlightFilter;
import com.ml.FlightsApi.model.FlightDTO;

import java.util.function.Predicate;

public class Origin extends FlightFilter {
    String origin;

    @Override
    public Predicate<FlightDTO> getPredicate() {
        return w -> w.getOrigin().equals(origin);
    }

    @Override
    public String getFilterName() {
        return "origin";
    }

    @Override
    public void setValue(String value){
        origin = value;
    }
}
