package com.ml.FlightsApi.model.request;

import com.ml.FlightsApi.model.PaymentMethodDTO;
import com.ml.FlightsApi.model.PeopleDTO;

import java.util.List;

public class NewFlightReservationDTO {
    String dateFrom, dateTo, origin, destination, flightNumber, seatType;
    Integer seats;
    List<PeopleDTO> people;
    PaymentMethodDTO paymentMethod;

    public PaymentMethodDTO getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodDTO paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public List<PeopleDTO> getPeople() {
        return people;
    }

    public void setPeople(List<PeopleDTO> people) {
        this.people = people;
    }
}
