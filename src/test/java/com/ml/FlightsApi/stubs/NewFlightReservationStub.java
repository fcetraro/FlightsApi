package com.ml.FlightsApi.stubs;

import com.ml.FlightsApi.model.PeopleDTO;
import com.ml.FlightsApi.model.request.NewFlightReservationDTO;

import java.util.ArrayList;
import java.util.List;

import static com.ml.FlightsApi.stubs.PaymentMethodStub.getCreditPaymentMethod;
import static com.ml.FlightsApi.stubs.PeopleStub.getPeople;


public class NewFlightReservationStub {
    public static NewFlightReservationDTO getFlightReservationStub(){
        List<PeopleDTO> people = new ArrayList<>();
        people.add(getPeople());
        people.add(getPeople());
        NewFlightReservationDTO flightReservationDTO = new NewFlightReservationDTO();
        flightReservationDTO.setDateFrom("10/02/2021");
        flightReservationDTO.setDateTo("20/02/2021");
        flightReservationDTO.setDestination("Bogotá");
        flightReservationDTO.setOrigin("Puerto Iguazú");
        flightReservationDTO.setFlightNumber("PIBA-1420");
        flightReservationDTO.setPeople(people);
        flightReservationDTO.setSeatType("ECONOMY");
        flightReservationDTO.setSeats(2);
        flightReservationDTO.setPaymentMethod(getCreditPaymentMethod());
        return flightReservationDTO;
    }
}
