package com.busbooking.service;
import java.util.List;

import com.busbooking.dto.BookingDTO;

public interface BookingService {

    BookingDTO createBooking(Long scheduleId, List<Long> seatIds, String userEmail);

    List<BookingDTO> getMyBookings(String userEmail);

    BookingDTO cancelBooking(Long bookingId, String userEmail);
}
