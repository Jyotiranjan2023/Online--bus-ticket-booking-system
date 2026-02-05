package com.busbooking.service;

import com.busbooking.entity.Route;
import java.util.List;

public interface RouteService {

    Route addRoute(Route route);

    List<Route> getAllRoutes();
}
