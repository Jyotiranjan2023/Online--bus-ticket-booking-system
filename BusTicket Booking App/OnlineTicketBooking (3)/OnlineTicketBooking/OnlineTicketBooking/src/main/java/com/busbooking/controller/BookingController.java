package com.busbooking.controller;

import com.busbooking.dto.BookingDTO;
import com.busbooking.service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // =========================
    // CREATE BOOKING
    // =========================
    @PostMapping
    public BookingDTO createBooking(
            @RequestParam Long scheduleId,
            @RequestParam List<Long> seatIds,
            Authentication authentication) {

        String email = authentication.getName();
        return bookingService.createBooking(scheduleId, seatIds, email);
    }

    // =========================
    // VIEW MY BOOKINGS
    // =========================
    @GetMapping("/my")
    public List<BookingDTO> myBookings(Authentication authentication) {

        String email = authentication.getName();
        return bookingService.getMyBookings(email);
    }

    // =========================
    // CANCEL BOOKING
    // =========================
    @PostMapping("/cancel/{bookingId}")
    public BookingDTO cancelBooking(
            @PathVariable Long bookingId,
            Authentication authentication) {

        String email = authentication.getName();
        return bookingService.cancelBooking(bookingId, email);
    }
}
