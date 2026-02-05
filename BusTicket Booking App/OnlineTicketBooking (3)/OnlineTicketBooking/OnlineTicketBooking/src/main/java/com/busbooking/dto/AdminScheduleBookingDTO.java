package com.busbooking.dto;

import java.util.List;

public class AdminScheduleBookingDTO {
	
	// Schedule info
    private Long scheduleId;
    private String busNumber;
    private String busType;
    private String source;
    private String destination;
    private String travelDate;
    private String departureTime;
    private double price;

    // Seat summary
    private int totalSeats;
    private int bookedSeats;
    private int availableSeats;
    
    

    public Long getScheduleId() {
		return scheduleId;
	}



	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}



	public String getBusNumber() {
		return busNumber;
	}



	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}



	public String getBusType() {
		return busType;
	}



	public void setBusType(String busType) {
		this.busType = busType;
	}



	public String getSource() {
		return source;
	}



	public void setSource(String source) {
		this.source = source;
	}



	public String getDestination() {
		return destination;
	}



	public void setDestination(String destination) {
		this.destination = destination;
	}



	public String getTravelDate() {
		return travelDate;
	}



	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}



	public String getDepartureTime() {
		return departureTime;
	}



	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public int getTotalSeats() {
		return totalSeats;
	}



	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}



	public int getBookedSeats() {
		return bookedSeats;
	}



	public void setBookedSeats(int bookedSeats) {
		this.bookedSeats = bookedSeats;
	}



	public int getAvailableSeats() {
		return availableSeats;
	}



	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}



	public List<PassengerBookingDTO> getPassengers() {
		return passengers;
	}



	public void setPassengers(List<PassengerBookingDTO> passengers) {
		this.passengers = passengers;
	}



	// Passenger details
    private List<PassengerBookingDTO> passengers;

}
