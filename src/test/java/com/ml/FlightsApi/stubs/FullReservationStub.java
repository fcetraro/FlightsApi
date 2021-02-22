package com.ml.FlightsApi.stubs;

import com.ml.FlightsApi.model.request.NewFullReservationDTO;

import static com.ml.FlightsApi.stubs.NewFlightReservationStub.getFlightReservationStub;

public class FullReservationStub {
    public static NewFullReservationDTO getFullNewReservationStub(){
        NewFullReservationDTO stub = new NewFullReservationDTO();
        stub.setUserName("facetraro@gmail.com");
        stub.setFlightReservation(getFlightReservationStub());
        return stub;
    }
}
