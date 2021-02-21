package com.ml.FlightsApi.filter.concret;

import com.ml.FlightsApi.filter.FlightFilter;
import com.ml.FlightsApi.model.FlightDTO;

import java.util.function.Predicate;

public class Destination extends FlightFilter {
    String destination;

    @Override
    public Predicate<FlightDTO> getPredicate() {
        return w -> w.getDestination().equals(destination);
    }

    @Override
    public String getFilterName() {
        return "destination";
    }

    @Override
    public void setValue(String value){
        destination = value;
    }
}
