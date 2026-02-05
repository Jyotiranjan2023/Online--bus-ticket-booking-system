package com.busbooking.dto;

import java.time.LocalDateTime;
import java.util.List;

public class BookingDTO {

    private Long bookingId;
    private String status;
    private LocalDateTime bookingTime;

    private String source;
    private String destination;
    private String travelDate;
    private String departureTime;
    private double price;

    private String ticketNumber;
    private String ticketIssuedAt;
    

    public String getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public String getTicketIssuedAt() {
		return ticketIssuedAt;
	}
	public void setTicketIssuedAt(String ticketIssuedAt) {
		this.ticketIssuedAt = ticketIssuedAt;
	}
	private List<SeatDTO> seats;

    public Long getBookingId() { 
    	return bookingId; 
    	}
    public String getStatus() { return status; }
    public LocalDateTime getBookingTime() { return bookingTime; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public String getTravelDate() { return travelDate; }
    public String getDepartureTime() { return departureTime; }
    public double getPrice() { return price; }
    public List<SeatDTO> getSeats() { return seats; }

    public void setBookingId(Long bookingId) { 
    	
    	this.bookingId = bookingId; }
    public void setStatus(String status) { 
    	this.status = status;
    	}
    public void setBookingTime(LocalDateTime bookingTime) { 
    	this.bookingTime = bookingTime;
    	}
    public void setSource(String source) { 
    	this.source = source; }
    public void setDestination(String destination) { 
    	this.destination = destination; 
    	}
    public void setTravelDate(String travelDate) { 
    	this.travelDate = travelDate; 
    	}
    public void setDepartureTime(String departureTime) { 
    	this.departureTime = departureTime; 
    	}
    public void setPrice(double price) { 
    	this.price = price; 
    	}
    public void setSeats(List<SeatDTO> seats) { 
    	this.seats = seats; 
    	}
}
