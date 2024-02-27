package com.example.asteroids.nasa.controller;

import com.example.asteroids.nasa.models.ApiResponse;
import com.example.asteroids.nasa.models.AsteroidResponse;
import com.example.asteroids.nasa.service.AsteroidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AsteroidController {

    private final AsteroidService asteroidService;

    @Autowired
    public AsteroidController(AsteroidService asteroidService) {
        this.asteroidService = asteroidService;
    }

    @GetMapping(
            path = "/api/asteroids",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ApiResponse<List<AsteroidResponse>> get(
            @RequestParam("start_date") String startDate,
            @RequestParam("end_date") String endDate
    ) {
        List<AsteroidResponse> response = asteroidService.getNearestAsteroid(startDate, endDate);
        return ApiResponse.<List<AsteroidResponse>>builder()
                .status("OK")
                .data(response)
                .build();
    }
}
