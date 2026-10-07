
package com.example.flightbaggage.controller;

import com.example.flightbaggage.entity.Flight;
import com.example.flightbaggage.service.FlightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@CrossOrigin(origins = "*")
public class FlightController {

    private final FlightService service;

    public FlightController(FlightService service) {
        this.service = service;
    }

    @GetMapping
    public List<Flight> getAllFlights() {
        return service.getAllFlights();
    }

    @GetMapping("/{id}")
    public Flight getFlightById(@PathVariable Integer id) {
        return service.getFlightById(id);
    }

    @PostMapping
    public Flight addFlight(@RequestBody Flight flight) {
        return service.addFlight(flight);
    }

    @PutMapping("/{id}")
    public Flight updateFlight(
            @PathVariable Integer id,
            @RequestBody Flight flight) {
        return service.updateFlight(id, flight);
    }

    @DeleteMapping("/{id}")
    public String deleteFlight(@PathVariable Integer id) {
        service.deleteFlight(id);
        return "Flight deleted successfully";
    }
    //sub query
    @GetMapping("/above-average-baggage")
    public List<Object[]> findFlightsAboveAverageBaggage() {
        return service.findFlightsAboveAverageBaggage();
    }
}