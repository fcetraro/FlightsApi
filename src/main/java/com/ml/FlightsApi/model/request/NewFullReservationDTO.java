package com.ml.FlightsApi.model.request;

import com.ml.FlightsApi.model.response.FlightReservationDTO;
import com.ml.FlightsApi.model.response.StatusCode;

public class NewFullReservationDTO {
    String userName;
    NewFlightReservationDTO flightReservation;

    public NewFlightReservationDTO getFlightReservation() {
        return flightReservation;
    }

    public void setFlightReservation(NewFlightReservationDTO flightReservation) {
        this.flightReservation = flightReservation;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
