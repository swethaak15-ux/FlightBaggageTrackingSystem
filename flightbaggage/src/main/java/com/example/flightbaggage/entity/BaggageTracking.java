
package com.example.flightbaggage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

@Entity
@Table(name = "BAGGAGE_TRACKING")
public class BaggageTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tracking_id")
    private Integer trackingId;

    @Column(name = "baggage_id")
    private Integer baggageId;

    private String status;

    private String location;

    @Column(name = "tracking_time")
    private LocalDateTime trackingTime;

    public Integer getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(Integer trackingId) {
        this.trackingId = trackingId;
    }

    public Integer getBaggageId() {
        return baggageId;
    }

    public void setBaggageId(Integer baggageId) {
        this.baggageId = baggageId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getTrackingTime() {
        return trackingTime;
    }

    public void setTrackingTime(LocalDateTime trackingTime) {
        this.trackingTime = trackingTime;
    }
}