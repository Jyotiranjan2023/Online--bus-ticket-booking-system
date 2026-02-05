package com.busbooking.controller;

import com.busbooking.entity.Route;
import com.busbooking.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    // ADMIN adds route
    @PostMapping
    public Route addRoute(@RequestBody Route route) {
        return routeService.addRoute(route);
    }

    // ADMIN views all routes
    @GetMapping
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }
}
