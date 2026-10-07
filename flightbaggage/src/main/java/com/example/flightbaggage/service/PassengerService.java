package com.example.flightbaggage.service;

import com.example.flightbaggage.entity.Passenger;
import com.example.flightbaggage.repository.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {

    private final PassengerRepository repository;

    public PassengerService(PassengerRepository repository) {
        this.repository = repository;
    }

    public List<Passenger> getAllPassengers() {
        return repository.findAll();
    }

    public Passenger getPassengerById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Passenger addPassenger(Passenger passenger) {
        return repository.save(passenger);
    }

    public Passenger updatePassenger(
            Integer id, Passenger passenger) {

        passenger.setPassengerId(id);
        return repository.save(passenger);
    }

    public void deletePassenger(Integer id) {
        repository.deleteById(id);
    }
}