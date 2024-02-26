package com.example.asteroids.nasa.client.response.neofeed;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class NeoFeedResponse{

	@JsonProperty("near_earth_objects")
	private Map<String, List<AsteroidObject>> nearEarthObjects;

	@JsonProperty("links")
	private Links links;
}