package com.busbooking.controller;

import com.busbooking.entity.Bus;
import com.busbooking.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/buses")
public class BusController {

    @Autowired
    private BusService busService;

    // ADMIN adds bus
    @PostMapping
    public Bus addBus(@RequestBody Bus bus) {
        return busService.addBus(bus);
    }

    // View all buses
    @GetMapping
    public List<Bus> getAllBuses() {
        return busService.getAllBuses();
    }
}
