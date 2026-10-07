
package com.example.flightbaggage.controller;

import com.example.flightbaggage.entity.Baggage;
import com.example.flightbaggage.service.BaggageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@RestController
@RequestMapping("/api/baggage")
@CrossOrigin(origins = "*")
public class BaggageController {

    private final BaggageService service;

    public BaggageController(BaggageService service) {
        this.service = service;
    }

    @GetMapping
    public List<Baggage> getAllBaggage() {
        return service.getAllBaggage();
    }

    @GetMapping("/{id}")
    public Baggage getBaggageById(@PathVariable Integer id) {
        return service.getBaggageById(id);
    }

    @PostMapping
    public Baggage addBaggage(@RequestBody Baggage baggage) {
        return service.addBaggage(baggage);
    }

    @PutMapping("/{id}")
    public Baggage updateBaggage(
            @PathVariable Integer id,
            @RequestBody Baggage baggage) {
        return service.updateBaggage(id, baggage);
    }

    @DeleteMapping("/{id}")
    public String deleteBaggage(@PathVariable Integer id) {
        service.deleteBaggage(id);
        return "Baggage deleted successfully";
    }

    @GetMapping("/details")
    public List<Object[]> getBaggageDetails() {
    	return service.getBaggageDetails();
    }
    
    //For procedure
    @PostMapping("/register")
    public String registerBaggage(
            @RequestParam int passengerId,
            @RequestParam int flightId,
            @RequestParam double weight) {

        service.registerBaggage(passengerId, flightId, weight);
        return "Baggage registered successfully";
    }

@GetMapping("/charges")
public List<Object[]> getBaggageCharges() {
    return service.getBaggageCharges();
}//for function

@PutMapping("/{id}/status")
public void updateStatus(
        @PathVariable int id,
        @RequestParam String status) {

    service.updateStatus(id, status);
}
}