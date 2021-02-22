package com.ml.FlightsApi.service.implementation;

import com.ml.FlightsApi.filter.FlightFilter;
import com.ml.FlightsApi.filter.FlightPredicate;
import com.ml.FlightsApi.filter.concret.*;
import com.ml.FlightsApi.model.FlightDTO;
import com.ml.FlightsApi.model.request.NewFlightReservationDTO;
import com.ml.FlightsApi.model.request.NewFullReservationDTO;
import com.ml.FlightsApi.model.response.FlightReservationDTO;
import com.ml.FlightsApi.model.response.ReservationDTO;
import com.ml.FlightsApi.repository.IFlightRepository;
import com.ml.FlightsApi.service.IFlightService;
import com.ml.FlightsApi.service.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;

import static com.ml.FlightsApi.util.StatusCodes.getFlightNotFoundStatusCode;
import static com.ml.FlightsApi.util.StatusCodes.getOkFlightStatusCode;
import static com.ml.FlightsApi.util.Validator.*;
import static java.util.stream.Collectors.toList;

@Service
public class ReservationService implements IReservationService {
    @Autowired
    private IFlightService flights;
    @Autowired
    public ReservationService(IFlightService flights) {
        this.flights = flights;
    }

    private Map<String, String> getFiltersForReservation(NewFlightReservationDTO flight){
        Map<String, String> filters = new HashMap<>();
        FlightFilter destinationFilter = new Destination();
        filters.put(destinationFilter.getFilterName(),flight.getDestination());
        FlightFilter codeFilter = new Code();
        filters.put(codeFilter.getFilterName(),flight.getFlightNumber());
        FlightFilter dateFrom = new DateFrom();
        filters.put(dateFrom.getFilterName(),flight.getDateFrom());
        FlightFilter dateTo = new DateTo();
        filters.put(dateTo.getFilterName(),flight.getDateTo());
        FlightFilter originFilter = new Origin();
        filters.put(originFilter.getFilterName(),flight.getOrigin());
        return filters;
    }
    @Override
    public ReservationDTO reserve(NewFullReservationDTO reservation){
        verifyReservation(reservation);
        return makeReservation(reservation);
    }

    private ReservationDTO makeReservation(NewFullReservationDTO reserve) {
        ReservationDTO response = new ReservationDTO();
        Map<String, String> filters = getFiltersForReservation(reserve.getFlightReservation());
        List<FlightDTO> flightFits = flights.get(filters);
        if(flightFits.size()!=0){
            FlightDTO bookingHotel = flightFits.get(0);
            response = setValues(reserve, response, bookingHotel);
            response.setStatusCode(getOkFlightStatusCode());
        } else {
            response.setStatusCode(getFlightNotFoundStatusCode());
        }
        return response;
    }

    private ReservationDTO setValues(NewFullReservationDTO reserve, ReservationDTO reservation, FlightDTO flight) {
        double amountWithoutInterest = flight.getPricePerPerson() * reserve.getFlightReservation().getSeats();
        double interest = getInterest(reserve.getFlightReservation().getPaymentMethod().getDues());
        reservation.setAmount(amountWithoutInterest);
        reservation.setInterest(interest);
        reservation.setTotal(amountWithoutInterest + (amountWithoutInterest*interest/100));
        reservation.setUserName(reserve.getUserName());
        reservation.setFlightReservation(getFlightReservationDTO(reserve.getFlightReservation()));
        return reservation;
    }

    private FlightReservationDTO getFlightReservationDTO(NewFlightReservationDTO newReserve) {
        FlightReservationDTO flight = new FlightReservationDTO();
        flight.setDateFrom(newReserve.getDateFrom());
        flight.setDateTo(newReserve.getDateTo());
        flight.setDestination(newReserve.getDestination());
        flight.setFlightNumber(newReserve.getFlightNumber());
        flight.setPeople(newReserve.getPeople());
        flight.setSeats(newReserve.getSeats());
        flight.setOrigin(newReserve.getOrigin());
        return flight;
    }

    private double getInterest(int dues) {
        int interest = 1;
        int firstInterest = 5;
        if(dues>1){
            interest = firstInterest * ((dues/3)+1);
        }
        return interest;
    }

    private void verifyReservation(NewFullReservationDTO reserve) {
        validateDatesString(reserve.getFlightReservation().getDateFrom(),reserve.getFlightReservation().getDateTo());
        validateCity(reserve.getFlightReservation().getDestination(), flights.get(new HashMap<>()));
        validateEmail(reserve.getUserName());
        validatePaymentMethod(reserve.getFlightReservation().getPaymentMethod());
    }
}
