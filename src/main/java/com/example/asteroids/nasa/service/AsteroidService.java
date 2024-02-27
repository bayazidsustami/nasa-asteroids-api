package com.example.asteroids.nasa.service;

import com.example.asteroids.nasa.models.AsteroidResponse;

import java.util.List;

public interface AsteroidService {

    public List<AsteroidResponse> getNearestAsteroid(String startDate, String endDate);
}
