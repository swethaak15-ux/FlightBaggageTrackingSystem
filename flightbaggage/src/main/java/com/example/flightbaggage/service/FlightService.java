
package com.example.flightbaggage.service;

import com.example.flightbaggage.entity.Flight;
import com.example.flightbaggage.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    private final FlightRepository repository;

    public FlightService(FlightRepository repository) {
        this.repository = repository;
    }

    public List<Flight> getAllFlights() {
        return repository.findAll();
    }

    public Flight getFlightById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Flight addFlight(Flight flight) {
        return repository.save(flight);
    }

    public Flight updateFlight(Integer id, Flight flight) {
        flight.setFlightId(id);
        return repository.save(flight);
    }

    public void deleteFlight(Integer id) {
        repository.deleteById(id);
    }
    //sub query
    public List<Object[]> findFlightsAboveAverageBaggage() {
        return repository.findFlightsAboveAverageBaggage();
    }
}