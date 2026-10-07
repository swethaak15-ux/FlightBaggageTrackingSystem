
package com.example.flightbaggage.controller;

import com.example.flightbaggage.entity.BaggageTracking;
import com.example.flightbaggage.service.BaggageTrackingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracking")
@CrossOrigin(origins = "*")
public class BaggageTrackingController {

    private final BaggageTrackingService service;

    public BaggageTrackingController(
            BaggageTrackingService service) {
        this.service = service;
    }

    @GetMapping
    public List<BaggageTracking> getAllTracking() {
        return service.getAllTracking();
    }

    @GetMapping("/{id}")
    public BaggageTracking getTrackingById(
            @PathVariable Integer id) {
        return service.getTrackingById(id);
    }

    @GetMapping("/baggage/{baggageId}")
    public List<BaggageTracking> getTrackingByBaggageId(
            @PathVariable Integer baggageId) {
        return service.getTrackingByBaggageId(baggageId);
    }

    @PostMapping
    public BaggageTracking addTracking(
            @RequestBody BaggageTracking tracking) {
        return service.addTracking(tracking);
    }

    @DeleteMapping("/{id}")
    public String deleteTracking(@PathVariable Integer id) {
        service.deleteTracking(id);
        return "Tracking record deleted successfully";
    }
}