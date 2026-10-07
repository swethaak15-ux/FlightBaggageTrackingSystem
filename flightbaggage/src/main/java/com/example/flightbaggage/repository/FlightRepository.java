
package com.example.flightbaggage.repository;

import com.example.flightbaggage.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface FlightRepository
        extends JpaRepository<Flight, Integer> {

    @Query(value = """
        SELECT f.flight_id, f.flight_number,
               f.source, f.destination,
               COUNT(b.baggage_id) AS baggage_count
        FROM FLIGHTS f
        LEFT JOIN BAGGAGE b ON f.flight_id = b.flight_id
        GROUP BY f.flight_id, f.flight_number,
                 f.source, f.destination
        HAVING COUNT(b.baggage_id) > (
            SELECT AVG(bag_count)
            FROM (
                SELECT COUNT(b2.baggage_id) AS bag_count
                FROM FLIGHTS f2
                LEFT JOIN BAGGAGE b2
                    ON f2.flight_id = b2.flight_id
                GROUP BY f2.flight_id
            ) AS average_counts
        )
        """, nativeQuery = true)
    List<Object[]> findFlightsAboveAverageBaggage();
}