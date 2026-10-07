
package com.example.flightbaggage.service;

import com.example.flightbaggage.entity.BaggageTracking;
import com.example.flightbaggage.repository.BaggageTrackingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaggageTrackingService {

    private final BaggageTrackingRepository repository;

    public BaggageTrackingService(
            BaggageTrackingRepository repository) {
        this.repository = repository;
    }

    public List<BaggageTracking> getAllTracking() {
        return repository.findAll();
    }

    public BaggageTracking getTrackingById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public List<BaggageTracking> getTrackingByBaggageId(
            Integer baggageId) {
        return repository.findByBaggageId(baggageId);
    }

    public BaggageTracking addTracking(
            BaggageTracking tracking) {
        if (tracking.getTrackingTime() == null) {
            tracking.setTrackingTime(
                    java.time.LocalDateTime.now());
        }
        return repository.save(tracking);
    }

    public void deleteTracking(Integer id) {
        repository.deleteById(id);
    }
}