
package com.example.flightbaggage.service;

import com.example.flightbaggage.entity.Baggage;
import com.example.flightbaggage.repository.BaggageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaggageService {

    private final BaggageRepository repository;

    public BaggageService(BaggageRepository repository) {
        this.repository = repository;
    }

    public List<Baggage> getAllBaggage() {
        return repository.findAll();
    }

    public Baggage getBaggageById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Baggage addBaggage(Baggage baggage) {
        if (baggage.getStatus() == null || baggage.getStatus().isBlank()) {
            baggage.setStatus("Checked In");
        }
        return repository.save(baggage);
    }

    public Baggage updateBaggage(Integer id, Baggage baggage) {
        baggage.setBaggageId(id);
        return repository.save(baggage);
    }

    public void deleteBaggage(Integer id) {
        repository.deleteById(id);
    }

    public List<Object[]> getBaggageDetails() {
    	return repository.getBaggageDetails();
    }
    public void registerBaggage(
            Integer passengerId,
            Integer flightId,
            Double weight) {

        repository.registerBaggage(
            passengerId, flightId, weight
        );
    }//for procedure

    public List<Object[]> getBaggageCharges() {
        return repository.getBaggageCharges();
    }//for function
    
    public void updateStatus(int id, String status) {
        Baggage baggage = repository.findById(id).orElseThrow();
        baggage.setStatus(status);
        repository.save(baggage);
    }
}