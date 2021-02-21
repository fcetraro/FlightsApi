package com.ml.FlightsApi.filter.concret;

import com.ml.FlightsApi.filter.FlightFilter;
import com.ml.FlightsApi.model.FlightDTO;

import java.util.function.Predicate;

public class Code extends FlightFilter {
    String code;

    @Override
    public Predicate<FlightDTO> getPredicate() {
        return w -> w.getCode().equals(code);
    }

    @Override
    public String getFilterName() {
        return "code";
    }

    @Override
    public void setValue(String value){
        code = value;
    }
}
