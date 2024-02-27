package com.example.asteroids.nasa.repository.neofeed;

import com.example.asteroids.nasa.client.response.neofeed.NeoFeedResponse;

public interface NeoFeedRepository {

    NeoFeedResponse getNeoFeed(String startDate, String endDate);

}
