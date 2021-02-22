package com.ml.FlightsApi.service;

import com.ml.FlightsApi.model.FlightDTO;
import com.ml.FlightsApi.model.request.NewFullReservationDTO;
import com.ml.FlightsApi.model.response.ReservationDTO;

import java.util.List;
import java.util.Map;

public interface IReservationService {
    ReservationDTO reserve(NewFullReservationDTO reservation);
}
