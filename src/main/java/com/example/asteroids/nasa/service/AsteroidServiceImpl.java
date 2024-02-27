package com.example.asteroids.nasa.service;

import com.example.asteroids.nasa.client.response.neofeed.AsteroidObject;
import com.example.asteroids.nasa.client.response.neofeed.NeoFeedResponse;
import com.example.asteroids.nasa.models.AsteroidResponse;
import com.example.asteroids.nasa.models.DetailAsteroidResponse;
import com.example.asteroids.nasa.repository.neofeed.NeoFeedRepository;
import com.example.asteroids.nasa.repository.neolookup.NeoLookupRepository;
import com.example.asteroids.nasa.service.mapper.AsteroidMapper;
import com.example.asteroids.nasa.util.DistanceComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class AsteroidServiceImpl implements AsteroidService{

    private final NeoFeedRepository neoFeedRepository;

    private final NeoLookupRepository neoLookupRepository;

    private final AsteroidMapper asteroidMapper;

    @Autowired
    public AsteroidServiceImpl(
            NeoFeedRepository neoFeedRepository,
            NeoLookupRepository neoLookupRepository,
            AsteroidMapper asteroidMapper
    ) {
        this.neoFeedRepository = neoFeedRepository;
        this.neoLookupRepository = neoLookupRepository;
        this.asteroidMapper = asteroidMapper;
    }

    @Override
    public List<AsteroidResponse> getNearestAsteroid(String startDate, String endDate) {
        if (isBlankOrEmpty(startDate) || isBlankOrEmpty(endDate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "startDate and endDate is required");
        }

        if (isDateRangeValid(startDate, endDate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "maximum range is 7 days");
        }

        NeoFeedResponse neoFeed = neoFeedRepository.getNeoFeed(startDate, endDate);

        return sortItem(asteroidMapper.mapListToResponses(neoFeed));
    }

    @Override
    public DetailAsteroidResponse getDetailAsteroid(String asteroidId, String startDate, String endDate) {
        if (asteroidId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "should determine asteroidId");
        }

        if (!(startDate.isBlank() && endDate.isBlank()) && isDateRangeValid(startDate, endDate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "maximum range is 7 days");
        }

        AsteroidObject detailAsteroid = neoLookupRepository.getDetailAsteroid(asteroidId);
        return asteroidMapper.mapAsteroidDetailToResponse(detailAsteroid);
    }

    private boolean isBlankOrEmpty(String value) {
        return value.isBlank() || value.isEmpty();
    }
    
    private List<AsteroidResponse> sortItem(List<AsteroidResponse> item) {
        item.sort(new DistanceComparator());

        List<AsteroidResponse> firstTenAsteroids;
        if (item.size() >= 10) {
            firstTenAsteroids = item.subList(0, 10);
        } else {
            firstTenAsteroids = item;
        }
        return firstTenAsteroids;
    }

    private boolean isDateRangeValid(String startDateStr, String endDateStr) {
        LocalDate startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate endDate = LocalDate.parse(endDateStr, DateTimeFormatter.ISO_LOCAL_DATE);

        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        return daysBetween > 7;
    }
}
