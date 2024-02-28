package com.example.asteroids.nasa.service;

import com.example.asteroids.nasa.models.AsteroidResponse;
import com.example.asteroids.nasa.models.DetailAsteroidResponse;
import com.example.asteroids.nasa.models.TotalAsteroidResponse;

import java.util.List;

public interface AsteroidService {

    List<AsteroidResponse> getNearestAsteroid(String startDate, String endDate);

    DetailAsteroidResponse getDetailAsteroid(String asteroidId, String startDate, String endDate);

    TotalAsteroidResponse getTotalAsteroidByDistance(String startDate, String endDate, String distance);
}
