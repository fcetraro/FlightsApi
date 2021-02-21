package com.ml.FlightsApi.service;

import com.ml.FlightsApi.model.FlightDTO;

import java.util.List;
import java.util.Map;

public interface IFlightService {
    List<FlightDTO> get(Map<String,String> map);
    void modifyAvailability(Map<String,String> filter, Map<String,String> map);
}
