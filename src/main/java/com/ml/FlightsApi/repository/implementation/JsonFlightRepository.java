package com.ml.FlightsApi.repository.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ml.FlightsApi.exception.implementation.NotValidDateException;
import com.ml.FlightsApi.model.FlightDTO;
import com.ml.FlightsApi.model.FlightJSONDTO;
import com.ml.FlightsApi.repository.IFlightRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ml.FlightsApi.util.DateFormat.DATE_FORMAT;

@Repository
public class JsonFlightRepository implements IFlightRepository {
    private final static String db = "flights.json";
    private static List<FlightDTO> flights;

    private List<FlightJSONDTO> loadFromFile() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:"+db);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<FlightJSONDTO>> typeRef = new TypeReference<>(){};
        List<FlightJSONDTO> flights = null;
        try{
            flights = objectMapper.readValue(file, typeRef);
        } catch (IOException e){
            e.printStackTrace();
        }
        return flights;
    }

    private void loadInitial(){
        if (flights==null){
            flights = parseFromJson(loadFromFile());
        }
    }

    private List<FlightDTO> parseFromJson(List<FlightJSONDTO> list) {
        List<FlightDTO> flightList = new ArrayList<>();
        for (FlightJSONDTO jsonDto:list) {
            try {
                Date dateFrom = new SimpleDateFormat(DATE_FORMAT).parse(jsonDto.getDateFrom());
                Date dateTo = new SimpleDateFormat(DATE_FORMAT).parse(jsonDto.getDateTo());
                FlightDTO flight = new FlightDTO();
                flight.setCode(jsonDto.getCode());
                flight.setDateFrom(dateFrom);
                flight.setDateTo(dateTo);
                flight.setDestination(jsonDto.getDestination());
                flight.setOrigin(jsonDto.getOrigin());
                flight.setPricePerPerson(jsonDto.getPricePerPerson());
                flight.setSeatType(jsonDto.getSeatType());
                flightList.add(flight);
            } catch (ParseException e){
                throw new NotValidDateException(e);
            }
        }
        return flightList;
    }

    @Override
    public List<FlightDTO> getAll() {
        loadInitial();
        return flights;
    }
}

