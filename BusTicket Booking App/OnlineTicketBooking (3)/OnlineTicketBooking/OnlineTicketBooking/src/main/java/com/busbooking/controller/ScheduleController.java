package com.busbooking.controller;

import com.busbooking.entity.Schedule;
import com.busbooking.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    // Add schedule (ADMIN)
    @PostMapping
    public Schedule addSchedule(
            @RequestParam Long busId,
            @RequestParam Long routeId,
            @RequestBody Schedule schedule) {

        return scheduleService.addSchedule(busId, routeId, schedule);
    }

    // View all schedules
    @GetMapping
    public List<Schedule> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }
}
