package com.ml.FlightsApi.filter;

import com.ml.FlightsApi.filter.concret.*;
import com.ml.FlightsApi.model.FlightDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FMFlightFilter {
    public static List<FlightFilter> getFilters(Map<String, String> mapFilters){
        List<FlightFilter> filters = new ArrayList<>();
        Iterator it = mapFilters.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            FlightFilter concretFilter = getConcretFilter((String)pair.getKey(), (String)pair.getValue());
            if(concretFilter!=null){
                filters.add(concretFilter);
            }
            it.remove();
        }
        return filters;
    }

    private static FlightFilter getConcretFilter(String filter, String value) {
        for (FlightFilter concretFilter:getAllFilters()) {
            if(concretFilter.matchFilterName(filter)) {
                try {
                    concretFilter.setValue(value);
                    return concretFilter;
                } catch (Exception e){
                    String message = "Tipo ingresado para el filtro "+concretFilter.getFilterName() +" no es valido.";
                    //throw new WrongCastFilterException(message, e);
                }
            }
        }
        return null;
        //throw new FilterNotFoundException("Filtro [" + filter + "] no reconocido.", new Exception());
    }

    private static List<FlightFilter> getAllFilters(){
        List<FlightFilter> allFilters = new ArrayList<>();
        allFilters.add(new DateFrom());
        allFilters.add(new DateTo());
        allFilters.add(new Destination());
        allFilters.add(new Origin());
        allFilters.add(new Code());
        return allFilters;
    }
}
