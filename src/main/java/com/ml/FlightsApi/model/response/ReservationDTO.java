package com.ml.FlightsApi.model.response;

public class ReservationDTO {
    String userName;
    double amount, interest, total;
    FlightReservationDTO flightReservation;
    StatusCode statusCode;

    public FlightReservationDTO getFlightReservation() {
        return flightReservation;
    }

    public void setFlightReservation(FlightReservationDTO flightReservation) {
        this.flightReservation = flightReservation;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }
}
