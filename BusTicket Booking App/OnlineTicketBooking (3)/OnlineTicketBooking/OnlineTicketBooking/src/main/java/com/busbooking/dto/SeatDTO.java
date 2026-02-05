package com.busbooking.dto;

public class SeatDTO {

    private Long id;
    private int seatNumber;
    private boolean booked;

    public Long getId() { return id; }
    public int getSeatNumber() { return seatNumber; }
    public boolean isBooked() { return booked; }

    public void setId(Long id) { this.id = id; }
    public void setSeatNumber(int seatNumber) { this.seatNumber = seatNumber; }
    public void setBooked(boolean booked) { this.booked = booked; }
}
