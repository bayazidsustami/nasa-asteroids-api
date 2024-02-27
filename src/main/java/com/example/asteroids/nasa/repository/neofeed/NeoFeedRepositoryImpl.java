package com.example.asteroids.nasa.repository.neofeed;

import com.example.asteroids.nasa.client.interceptors.ApiClientConfig;
import com.example.asteroids.nasa.client.response.neofeed.NeoFeedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@Repository
public class NeoFeedRepositoryImpl implements NeoFeedRepository{

    private static final String NEO_FEED_ENDPOINT = ApiClientConfig.BASE_URL + "feed";
    private final RestTemplate restTemplate;

    @Autowired
    public NeoFeedRepositoryImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public NeoFeedResponse getNeoFeed(String startDate, String endDate) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(NEO_FEED_ENDPOINT)
                        .queryParam("start_date", startDate)
                        .queryParam("end_data", endDate);
        ResponseEntity<NeoFeedResponse> responseEntity = restTemplate.getForEntity(uriComponentsBuilder.toUriString(), NeoFeedResponse.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        }

        throw new ResponseStatusException(responseEntity.getStatusCode(), responseEntity.toString());
    }
}
