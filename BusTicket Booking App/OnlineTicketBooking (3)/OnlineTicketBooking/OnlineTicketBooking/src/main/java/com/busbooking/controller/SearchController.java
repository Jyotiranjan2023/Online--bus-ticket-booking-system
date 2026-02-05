package com.busbooking.controller;

import com.busbooking.entity.Schedule;
import com.busbooking.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ScheduleService scheduleService;

    // Passenger search API
    @GetMapping
    public List<Schedule> searchBuses(
            @RequestParam String source,
            @RequestParam String destination,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {

        return scheduleService.searchSchedules(source, destination, date);
    }
}
