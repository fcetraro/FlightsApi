package com.ml.FlightsApi.service.implementation;

import com.ml.FlightsApi.filter.FlightFilter;
import com.ml.FlightsApi.filter.FlightPredicate;
import com.ml.FlightsApi.filter.concret.Destination;
import com.ml.FlightsApi.filter.concret.Origin;
import com.ml.FlightsApi.model.FlightDTO;
import com.ml.FlightsApi.repository.IFlightRepository;
import com.ml.FlightsApi.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static com.ml.FlightsApi.util.Validator.*;
import static java.util.stream.Collectors.toList;

@Service
public class FlightService implements IFlightService {
    @Autowired
    private IFlightRepository flights;
    @Autowired
    public FlightService(IFlightRepository flightRepository) {
        this.flights = flightRepository;
    }

    private List<FlightDTO> applyFilters(List<FlightDTO> flights, Map<String, String> filter){
        FlightPredicate filters = new FlightPredicate();
        Predicate<FlightDTO> predicate = filters.getCombinedPredicateFromDTO(filter);
        return flights.stream().filter(predicate).collect(toList());
    }


    private void validateFilters(Map<String, String> filters){
        if(filters!=null) {
            FlightFilter destination = new Destination();
            if (filters.containsKey(destination.getFilterName())) {
                validateCity(filters.get(destination.getFilterName()),flights.getAll());
            }
            FlightFilter origin = new Origin();
            if (filters.containsKey(origin.getFilterName())) {
                validateCity(filters.get(origin.getFilterName()),flights.getAll());
            }
            if (filters.containsKey("dateTo") && filters.containsKey("dateFrom")) {
                validateDates(filters);
            }
        }
    }

    private void validateDates(Map<String, String> filters){
        validateDatesString(filters.get("dateFrom"),filters.get("dateTo"));
    }

    @Override
    public List<FlightDTO> get(Map<String, String> filters) {
        validateFilters(filters);
        return applyFilters(flights.getAll(), filters);
    }

    @Override
    public void modifyAvailability(Map<String, String> filter, Map<String, String> map) {

    }
}
