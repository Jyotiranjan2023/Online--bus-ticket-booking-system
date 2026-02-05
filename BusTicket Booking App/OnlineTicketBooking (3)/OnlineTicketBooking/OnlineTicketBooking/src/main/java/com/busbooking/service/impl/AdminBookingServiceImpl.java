package com.busbooking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.busbooking.dto.AdminScheduleBookingDTO;
import com.busbooking.dto.PassengerBookingDTO;
import com.busbooking.entity.Booking;
import com.busbooking.entity.Schedule;
import com.busbooking.entity.Seat;
import com.busbooking.exception.ResourceNotFoundException;
import com.busbooking.repository.BookingRepository;
import com.busbooking.repository.ScheduleRepository;
import com.busbooking.repository.SeatRepository;
import com.busbooking.service.AdminBookingService;
@Service
public class AdminBookingServiceImpl implements AdminBookingService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public AdminScheduleBookingDTO getScheduleBookingOverview(Long scheduleId) {

        Schedule schedule = scheduleRepository.findById(scheduleId)
            .orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));

        // Fetch all seats of this schedule
        List<Seat> allSeats = seatRepository.findBySchedule(schedule);

        // Fetch only CONFIRMED bookings
        List<Booking> bookings =
                bookingRepository.findByScheduleAndStatus(schedule, "CONFIRMED");

        int totalSeats = allSeats.size();
        int bookedSeats = allSeats.stream()
                .filter(seat -> seat.isBooked())
                .mapToInt(seat -> 1)
                .sum();

        List<PassengerBookingDTO> passengers = bookings.stream()
            .map(booking -> {
                PassengerBookingDTO dto = new PassengerBookingDTO();
                dto.setPassengerName(booking.getUser().getName());
                dto.setPassengerEmail(booking.getUser().getEmail());
                dto.setSeatNumbers(
                        booking.getSeats().stream()
                                .map(Seat::getSeatNumber)
                                .toList()
                );
                dto.setBookingStatus(booking.getStatus());
                dto.setBookingTime(booking.getBookingTime().toString());
                dto.setTicketNumber(booking.getTicketNumber());
                return dto;
            }).toList();

        AdminScheduleBookingDTO dto = new AdminScheduleBookingDTO();
        dto.setScheduleId(schedule.getId());
        dto.setBusNumber(schedule.getBus().getBusNumber());
        dto.setBusType(schedule.getBus().getBusType());
        dto.setSource(schedule.getRoute().getSource());
        dto.setDestination(schedule.getRoute().getDestination());
        dto.setTravelDate(schedule.getTravelDate().toString());
        dto.setDepartureTime(schedule.getDepartureTime().toString());
        dto.setPrice(schedule.getPrice());
        dto.setTotalSeats(totalSeats);
        dto.setBookedSeats(bookedSeats);
        dto.setAvailableSeats(totalSeats - bookedSeats);
        dto.setPassengers(passengers);

        return dto;
    }
}
