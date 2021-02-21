package com.ml.FlightsApi.repository;

import com.ml.FlightsApi.model.FlightDTO;

import java.util.List;

public interface IFlightRepository {
    List<FlightDTO> getAll();
}
