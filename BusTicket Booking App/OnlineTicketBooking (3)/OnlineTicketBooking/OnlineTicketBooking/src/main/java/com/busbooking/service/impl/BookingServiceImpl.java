package com.busbooking.service.impl;

import com.busbooking.dto.BookingDTO;
import com.busbooking.dto.SeatDTO;
import com.busbooking.entity.Booking;
import com.busbooking.entity.Schedule;
import com.busbooking.entity.Seat;
import com.busbooking.entity.User;
import com.busbooking.exception.BadRequestException;
import com.busbooking.exception.ResourceNotFoundException;
import com.busbooking.exception.UnauthorizedException;
import com.busbooking.repository.BookingRepository;
import com.busbooking.repository.ScheduleRepository;
import com.busbooking.repository.SeatRepository;
import com.busbooking.repository.UserRepository;
import com.busbooking.service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private SeatRepository seatRepository;

    // =========================
    // CREATE BOOKING
    // =========================
    @Override
    public BookingDTO createBooking(Long scheduleId, List<Long> seatIds, String userEmail) {

        // 1️⃣ Get user
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        // 2️⃣ Get schedule
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));

        // 3️⃣ Get seats
        List<Seat> seats = seatRepository.findAllById(seatIds);

        // 4️⃣ Validate seats
        if (seats.isEmpty()) {
            throw new BadRequestException("No seats selected for booking");
        }

        // 5️⃣ Lock seats
        for (Seat seat : seats) {
            if (seat.isBooked()) {
                throw new BadRequestException(
                        "Seat " + seat.getSeatNumber() + " already booked");
            }
            seat.setBooked(true);
            seatRepository.save(seat);
        }

        // 6️⃣ Create booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setSchedule(schedule);
        booking.setSeats(seats);
        booking.setStatus("CONFIRMED");
        booking.setBookingTime(LocalDateTime.now());

        // 🎟️ 7️⃣ Ticket generation
        booking.setTicketNumber(generateTicketNumber());
        booking.setTicketIssuedAt(LocalDateTime.now());

        // 8️⃣ Save booking
        Booking savedBooking = bookingRepository.save(booking);

        // 9️⃣ Return DTO
        return mapToDTO(savedBooking);
    }

    // =========================
    // GET MY BOOKINGS
    // =========================
    @Override
    public List<BookingDTO> getMyBookings(String userEmail) {

        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        return bookingRepository.findByUser(user)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // =========================
    // CANCEL BOOKING
    // =========================
    @Override
    public BookingDTO cancelBooking(Long bookingId, String userEmail) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        if (!booking.getUser().getEmail().equals(userEmail)) {
            throw new UnauthorizedException("You are not allowed to cancel this booking");
        }

        if ("CANCELLED".equals(booking.getStatus())) {
            throw new BadRequestException("Booking already cancelled");
        }

        for (Seat seat : booking.getSeats()) {
            seat.setBooked(false);
            seatRepository.save(seat);
        }

        booking.setStatus("CANCELLED");

        return mapToDTO(bookingRepository.save(booking));
    }

    // =========================
    // ENTITY → DTO MAPPER
    // =========================
    private BookingDTO mapToDTO(Booking booking) {

        BookingDTO dto = new BookingDTO();
        dto.setBookingId(booking.getId());
        dto.setStatus(booking.getStatus());
        dto.setBookingTime(booking.getBookingTime());
        dto.setTicketNumber(booking.getTicketNumber());
        dto.setTicketIssuedAt(booking.getTicketIssuedAt().toString());

        dto.setSource(booking.getSchedule().getRoute().getSource());
        dto.setDestination(booking.getSchedule().getRoute().getDestination());
        dto.setTravelDate(booking.getSchedule().getTravelDate().toString());
        dto.setDepartureTime(booking.getSchedule().getDepartureTime().toString());
        dto.setPrice(booking.getSchedule().getPrice());

        List<SeatDTO> seatDTOs = booking.getSeats().stream().map(seat -> {
            SeatDTO s = new SeatDTO();
            s.setId(seat.getId());
            s.setSeatNumber(seat.getSeatNumber());
            s.setBooked(seat.isBooked());
            return s;
        }).toList();

        dto.setSeats(seatDTOs);

        return dto;
    }

    // =========================
    // TICKET NUMBER GENERATOR
    // =========================
    private String generateTicketNumber() {
        return "TKT-" + System.currentTimeMillis();
    }
}
