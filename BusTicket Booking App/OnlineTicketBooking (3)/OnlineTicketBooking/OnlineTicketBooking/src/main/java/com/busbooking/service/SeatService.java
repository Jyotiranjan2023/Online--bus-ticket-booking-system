package com.busbooking.service;

import com.busbooking.entity.Seat;
import java.util.List;

public interface SeatService {

    Seat addSeat(Long scheduleId, int seatNumber);

    List<Seat> getAvailableSeats(Long scheduleId);

    Seat bookSeat(Long seatId);
}
