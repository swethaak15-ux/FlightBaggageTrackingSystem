
package com.example.flightbaggage.repository;

import com.example.flightbaggage.entity.BaggageTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BaggageTrackingRepository
        extends JpaRepository<BaggageTracking, Integer> {

    List<BaggageTracking> findByBaggageId(Integer baggageId);

    @Query(value = """
        SELECT t.tracking_id, t.baggage_id,
               t.status, t.location, t.tracking_time,
               b.weight
        FROM BAGGAGE_TRACKING t
        JOIN BAGGAGE b
            ON t.baggage_id = b.baggage_id
        """, nativeQuery = true)
    List<Object[]> getTrackingDetails();
}