package com.busbooking.repository;

import com.busbooking.entity.Seat;
import com.busbooking.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findByScheduleAndBookedFalse(Schedule schedule);
    List<Seat> findBySchedule(Schedule schedule);
}
