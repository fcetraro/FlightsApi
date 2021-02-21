package com.ml.FlightsApi.controller;

import com.ml.FlightsApi.model.FlightDTO;
import com.ml.FlightsApi.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/flights")
public class FlightController {
    private IFlightService service;
    @Autowired
    public FlightController(IFlightService service) {
        this.service = service;
    }
    @GetMapping("")
    public List<FlightDTO> getHotels(@RequestParam(required = false) Map<String, String> queryMap){
        return service.get(queryMap);
    }
}
