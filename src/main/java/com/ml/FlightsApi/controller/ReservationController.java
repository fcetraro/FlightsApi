package com.ml.FlightsApi.controller;

import com.ml.FlightsApi.model.request.NewFullReservationDTO;
import com.ml.FlightsApi.model.response.ReservationDTO;
import com.ml.FlightsApi.service.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/flight-reservation")
public class ReservationController {
    private IReservationService service;
    @Autowired
    public ReservationController(IReservationService service) {
        this.service = service;
    }
    @PostMapping("")
    public ReservationDTO booking(@RequestBody NewFullReservationDTO reservation){
        return service.reserve(reservation);
    }
}
