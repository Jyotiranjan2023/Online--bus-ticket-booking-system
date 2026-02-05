package com.busbooking.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // who booked
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // which schedule
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    // which seats
    @ManyToMany
    @JoinTable(
        name = "booking_seats",
        joinColumns = @JoinColumn(name = "booking_id"),
        inverseJoinColumns = @JoinColumn(name = "seat_id")
    )
    private List<Seat> seats;

    // 🎟️ ticket info (NOT a relation)
    @Column(unique = true, nullable = false)
    private String ticketNumber;

    private LocalDateTime ticketIssuedAt;

    private String status; // CONFIRMED / CANCELLED

    private LocalDateTime bookingTime;

    // getters & setters
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public LocalDateTime getTicketIssuedAt() {
        return ticketIssuedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public void setTicketIssuedAt(LocalDateTime ticketIssuedAt) {
        this.ticketIssuedAt = ticketIssuedAt;
    }
}
