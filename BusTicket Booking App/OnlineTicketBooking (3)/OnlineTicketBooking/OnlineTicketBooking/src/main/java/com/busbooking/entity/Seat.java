package com.busbooking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int seatNumber;

    private boolean booked;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    // ---- Getters & Setters ----
    public Long getId() {
        return id;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public boolean isBooked() {
        return booked;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
