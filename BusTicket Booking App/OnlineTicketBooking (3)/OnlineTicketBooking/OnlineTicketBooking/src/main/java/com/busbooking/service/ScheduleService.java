package com.busbooking.service;

import com.busbooking.entity.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {

    Schedule addSchedule(Long busId, Long routeId, Schedule schedule);

    List<Schedule> getAllSchedules();
    List<Schedule> searchSchedules(String source, String destination, LocalDate date);
}
