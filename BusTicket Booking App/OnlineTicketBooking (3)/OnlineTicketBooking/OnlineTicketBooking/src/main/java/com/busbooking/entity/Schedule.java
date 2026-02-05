package com.busbooking.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    private LocalDate travelDate;
    private LocalTime departureTime;
    private double price;

    // ----- Getters -----
    public Long getId() {
        return id;
    }

    public Bus getBus() {
        return bus;
    }

    public Route getRoute() {
        return route;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public double getPrice() {
        return price;
    }

    // ----- Setters -----
    public void setId(Long id) {
        this.id = id;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
