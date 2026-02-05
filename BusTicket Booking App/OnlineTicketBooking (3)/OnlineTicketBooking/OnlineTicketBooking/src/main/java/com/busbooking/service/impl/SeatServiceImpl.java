package com.busbooking.service.impl;

import com.busbooking.entity.Schedule;
import com.busbooking.entity.Seat;
import com.busbooking.repository.ScheduleRepository;
import com.busbooking.repository.SeatRepository;
import com.busbooking.service.SeatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public Seat addSeat(Long scheduleId, int seatNumber) {

        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        Seat seat = new Seat();
        seat.setSeatNumber(seatNumber);
        seat.setBooked(false);
        seat.setSchedule(schedule);

        return seatRepository.save(seat);
    }

    @Override
    public List<Seat> getAvailableSeats(Long scheduleId) {

        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        return seatRepository.findByScheduleAndBookedFalse(schedule);
    }

    @Override
    public Seat bookSeat(Long seatId) {

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (seat.isBooked()) {
            throw new RuntimeException("Seat already booked");
        }

        seat.setBooked(true);
        return seatRepository.save(seat);
    }
}
