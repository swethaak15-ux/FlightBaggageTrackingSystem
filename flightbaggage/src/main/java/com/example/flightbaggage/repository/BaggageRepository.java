
package com.example.flightbaggage.repository;

import com.example.flightbaggage.entity.Baggage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface BaggageRepository
        extends JpaRepository<Baggage, Integer> {

    @Query(value = """
        SELECT b.baggage_id, p.name,
               f.flight_number, b.weight, b.status
        FROM BAGGAGE b
        JOIN PASSENGERS p
            ON b.passenger_id = p.passenger_id
        JOIN FLIGHTS f
            ON b.flight_id = f.flight_id
        """, nativeQuery = true)
    List<Object[]> getBaggageDetails();
    
    @Modifying
    @Transactional
    @Query(value = "CALL RegisterBaggage(:passengerId, :flightId, :weight)",
           nativeQuery = true)
    void registerBaggage(
            @Param("passengerId") int passengerId,
            @Param("flightId") int flightId,
            @Param("weight") double weight
    );//for procedure

    @Query(value = """
        SELECT baggage_id,
               weight,
               CalculateExcessCharge(weight) AS excess_charge
        FROM BAGGAGE
        """, nativeQuery = true)
    List<Object[]> getBaggageCharges();//for function
}