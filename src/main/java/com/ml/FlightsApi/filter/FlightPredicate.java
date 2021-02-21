package com.ml.FlightsApi.filter;

import com.ml.FlightsApi.model.FlightDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static com.ml.FlightsApi.filter.FMFlightFilter.getFilters;

public class FlightPredicate {
    public Predicate<FlightDTO> getCombinedPredicateFromDTO(Map<String, String> filters){
        return getCombinedPredicate(filters);
    }
    private Predicate<FlightDTO> getCombinedPredicate(Map<String, String> mapFilters){
        List<FlightFilter> allFilters = getFilters(mapFilters);
        List<Predicate<FlightDTO>> allPredicates = new ArrayList<>();
        for (FlightFilter filter:allFilters) {
            allPredicates.add(filter.getPredicate());
        }
        return allPredicates.stream().reduce(w -> true, Predicate::and);
    }
}
