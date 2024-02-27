package com.example.asteroids.nasa.client.response.neofeed;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RelativeVelocity{

	@JsonProperty("kilometers_per_second")
	private String kilometersPerHour;

	@JsonProperty("kilometers_per_hour")
	private String kilometersPerSecond;

	@JsonProperty("miles_per_hour")
	private String milesPerHour;
}