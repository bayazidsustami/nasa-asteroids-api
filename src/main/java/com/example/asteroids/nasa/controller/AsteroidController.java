package com.example.asteroids.nasa.controller;

import com.example.asteroids.nasa.models.ApiResponse;
import com.example.asteroids.nasa.models.AsteroidResponse;
import com.example.asteroids.nasa.models.DetailAsteroidResponse;
import com.example.asteroids.nasa.models.TotalAsteroidResponse;
import com.example.asteroids.nasa.service.AsteroidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(
            path = "/api/asteroids/{asteroidId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ApiResponse<DetailAsteroidResponse> getById(
            @PathVariable("asteroidId") String asteroidId,
            @RequestParam(value = "start_date", required = false, defaultValue = "") String startDate,
            @RequestParam(value = "end_date", required = false, defaultValue = "") String endDate
    ) {
        DetailAsteroidResponse detailAsteroid = asteroidService.getDetailAsteroid(asteroidId, startDate, endDate);
        return ApiResponse.<DetailAsteroidResponse>builder()
                .status("OK")
                .data(detailAsteroid)
                .build();
    }

    @GetMapping(
            path = "/api/asteroids/total",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public TotalAsteroidResponse getTotal(
            @RequestParam("start_date") String startDate,
            @RequestParam("end_date") String endDate,
            @RequestParam(value = "distance") String distance
    ) {
        TotalAsteroidResponse totalAsteroidByDistance = asteroidService.getTotalAsteroidByDistance(startDate, endDate, distance);
        return totalAsteroidByDistance;
    }
}
