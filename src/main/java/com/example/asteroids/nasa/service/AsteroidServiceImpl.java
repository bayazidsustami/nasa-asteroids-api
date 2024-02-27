package com.example.asteroids.nasa.service;

import com.example.asteroids.nasa.client.response.neofeed.NeoFeedResponse;
import com.example.asteroids.nasa.models.AsteroidResponse;
import com.example.asteroids.nasa.repository.neofeed.NeoFeedRepository;
import com.example.asteroids.nasa.service.mapper.AsteroidMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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

        if (!isDateRangeValid(startDate, endDate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "maximum range is 7 days");
        }

        NeoFeedResponse neoFeed = neoFeedRepository.getNeoFeed(startDate, endDate);

        return asteroidMapper.mapListToResponses(neoFeed);
    }

    private boolean isDateRangeValid(String startDateStr, String endDateStr) {
        LocalDate startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate endDate = LocalDate.parse(endDateStr, DateTimeFormatter.ISO_LOCAL_DATE);

        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        return daysBetween <= 7;
    }
}
