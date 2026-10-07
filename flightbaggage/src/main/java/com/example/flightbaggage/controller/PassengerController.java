package com.example.flightbaggage.controller;

import com.example.flightbaggage.entity.Passenger;
import com.example.flightbaggage.service.PassengerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
@CrossOrigin(origins = "*")
public class PassengerController {

    private final PassengerService service;

    public PassengerController(PassengerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Passenger> getAllPassengers() {
        return service.getAllPassengers();
    }

    @GetMapping("/{id}")
    public Passenger getPassengerById(@PathVariable Integer id) {
        return service.getPassengerById(id);
    }

    @PostMapping
    public Passenger addPassenger(@RequestBody Passenger passenger) {
        return service.addPassenger(passenger);
    }

    @PutMapping("/{id}")
    public Passenger updatePassenger(
            @PathVariable Integer id,
            @RequestBody Passenger passenger) {

        return service.updatePassenger(id, passenger);
    }

    @DeleteMapping("/{id}")
    public String deletePassenger(@PathVariable Integer id) {
        service.deletePassenger(id);
        return "Passenger deleted successfully";
    }
}