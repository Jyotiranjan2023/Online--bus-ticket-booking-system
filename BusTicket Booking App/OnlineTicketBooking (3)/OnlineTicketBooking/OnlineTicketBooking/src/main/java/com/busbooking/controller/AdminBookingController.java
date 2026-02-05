package com.busbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.busbooking.dto.AdminScheduleBookingDTO;
import com.busbooking.service.AdminBookingService;

@RestController
@RequestMapping("/admin/bookings")
@PreAuthorize("hasRole('ADMIN')")
public class AdminBookingController {

    @Autowired
    private AdminBookingService adminBookingService;

    @GetMapping("/overview/{scheduleId}")
    public AdminScheduleBookingDTO getBookingOverview(
            @PathVariable Long scheduleId) {

        return adminBookingService.getScheduleBookingOverview(scheduleId);
    }
}
