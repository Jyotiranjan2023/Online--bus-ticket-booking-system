package com.busbooking.service;

import com.busbooking.dto.AdminScheduleBookingDTO;

public interface AdminBookingService {
	
	 AdminScheduleBookingDTO getScheduleBookingOverview(Long scheduleId);
}