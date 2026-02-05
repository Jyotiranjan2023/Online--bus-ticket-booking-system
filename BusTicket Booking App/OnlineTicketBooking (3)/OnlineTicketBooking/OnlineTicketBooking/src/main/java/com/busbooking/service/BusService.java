package com.busbooking.service;

import com.busbooking.entity.Bus;
import java.util.List;

public interface BusService {

    Bus addBus(Bus bus);

    List<Bus> getAllBuses();
}
