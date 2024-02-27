package com.example.asteroids.nasa.repository.neolookup;

import com.example.asteroids.nasa.client.interceptors.ApiClientConfig;
import com.example.asteroids.nasa.client.response.neofeed.AsteroidObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@Repository
public class NeoLookupRepositoryImpl implements NeoLookupRepository{

    private static final String NEO_LOOKUP_ENDPOINT = ApiClientConfig.BASE_URL + "neo/";
    private final RestTemplate restTemplate;

    @Autowired
    public NeoLookupRepositoryImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public AsteroidObject getDetailAsteroid(String asteroidId) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(NEO_LOOKUP_ENDPOINT)
                .path(asteroidId);
        ResponseEntity<AsteroidObject> responseEntity = restTemplate.getForEntity(
                uriComponentsBuilder.toUriString(),
                AsteroidObject.class
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        }

        throw new ResponseStatusException(responseEntity.getStatusCode(), responseEntity.toString());
    }
}
