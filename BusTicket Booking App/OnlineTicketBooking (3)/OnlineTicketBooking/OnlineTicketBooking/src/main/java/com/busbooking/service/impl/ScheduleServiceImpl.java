package com.busbooking.service.impl;

import java.time.LocalDate;
import java.util.List;

import com.busbooking.entity.Bus;
import com.busbooking.entity.Route;
import com.busbooking.entity.Schedule;
import com.busbooking.repository.BusRepository;
import com.busbooking.repository.RouteRepository;
import com.busbooking.repository.ScheduleRepository;
import com.busbooking.service.ScheduleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Schedule addSchedule(Long busId, Long routeId, Schedule schedule) {

        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new RuntimeException("Route not found"));

        schedule.setBus(bus);
        schedule.setRoute(route);

        return scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> searchSchedules(String source, String destination, LocalDate date) {
        return scheduleRepository
                .findByRouteSourceAndRouteDestinationAndTravelDate(
                        source, destination, date);
    }
}
