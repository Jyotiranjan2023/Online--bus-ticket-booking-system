package com.busbooking.controller;

import com.busbooking.entity.Seat;
import com.busbooking.service.SeatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SeatController {

    @Autowired
    private SeatService seatService;

    // ==========================
    // ADMIN – ADD SEAT
    // ==========================
    @PostMapping("/admin/seats")
    public Seat addSeat(
            @RequestParam Long scheduleId,
            @RequestParam int seatNumber) {

        return seatService.addSeat(scheduleId, seatNumber);
    }

    // ==========================
    // PASSENGER – VIEW AVAILABLE SEATS
    // ==========================
    @GetMapping("/seats/available/{scheduleId}")
    public List<Seat> getAvailableSeats(@PathVariable Long scheduleId) {
        return seatService.getAvailableSeats(scheduleId);
    }

    // ==========================
    // PASSENGER – BOOK SEAT
    // ==========================
    @PostMapping("/seats/book/{seatId}")
    public Seat bookSeat(@PathVariable Long seatId) {
        return seatService.bookSeat(seatId);
    }
}
