
package com.example.flightbaggage.repository;

import com.example.flightbaggage.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PassengerRepository
        extends JpaRepository<Passenger, Integer> {

    @Query(value = """
        SELECT p.passenger_id, p.name, p.phone,
               b.baggage_id, b.weight, b.status,
               f.flight_number, f.source, f.destination
        FROM PASSENGERS p
        JOIN BAGGAGE b ON p.passenger_id = b.passenger_id
        JOIN FLIGHTS f ON b.flight_id = f.flight_id
        """, nativeQuery = true)
    List<Object[]> getPassengerBaggageDetails();
}