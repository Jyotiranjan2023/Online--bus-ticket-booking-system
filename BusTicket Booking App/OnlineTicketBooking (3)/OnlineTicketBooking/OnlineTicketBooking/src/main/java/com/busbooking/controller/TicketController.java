package com.busbooking.controller;

import com.busbooking.entity.Booking;
import com.busbooking.repository.BookingRepository;
import com.busbooking.service.TicketPdfService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class TicketController {

    private final BookingRepository bookingRepository;

    public TicketController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @GetMapping("/{bookingId}/ticket")
    public ResponseEntity<byte[]> downloadTicket(
            @PathVariable Long bookingId,
            Authentication authentication) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // ownership check
        if (!booking.getUser().getEmail().equals(authentication.getName())) {
            throw new RuntimeException("You are not allowed to access this ticket");
        }

        byte[] pdf = TicketPdfService.generateTicketPdf(booking);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=ticket-" + booking.getTicketNumber() + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
