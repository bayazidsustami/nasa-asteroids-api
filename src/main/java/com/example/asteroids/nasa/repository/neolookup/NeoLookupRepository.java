package com.example.asteroids.nasa.repository.neolookup;

import com.example.asteroids.nasa.client.response.neofeed.AsteroidObject;

public interface NeoLookupRepository {

    AsteroidObject getDetailAsteroid(String asteroidId);

}
