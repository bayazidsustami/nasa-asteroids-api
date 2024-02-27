package com.example.asteroids.nasa.service;

import com.example.asteroids.nasa.client.response.neofeed.NeoFeedResponse;
import com.example.asteroids.nasa.models.AsteroidResponse;
import com.example.asteroids.nasa.repository.neofeed.NeoFeedRepository;
import com.example.asteroids.nasa.service.mapper.AsteroidMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class AsteroidServiceImpl implements AsteroidService{

    private final NeoFeedRepository neoFeedRepository;

    private final AsteroidMapper asteroidMapper;

    @Autowired
    public AsteroidServiceImpl(
            NeoFeedRepository neoFeedRepository,
            AsteroidMapper asteroidMapper
    ) {
        this.neoFeedRepository = neoFeedRepository;
        this.asteroidMapper = asteroidMapper;
    }

    @Override
    public List<AsteroidResponse> getNearestAsteroid(String startDate, String endDate) {
        if (Objects.isNull(startDate) || Objects.isNull(endDate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "startDate and endDate is required");
        }

        NeoFeedResponse neoFeed = neoFeedRepository.getNeoFeed(startDate, endDate);

        return asteroidMapper.mapListToResponses(neoFeed);
    }
}
