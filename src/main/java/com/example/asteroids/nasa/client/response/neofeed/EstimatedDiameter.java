package com.example.asteroids.nasa.client.response.neofeed;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EstimatedDiameter{
	@JsonProperty("feet")
	private MinMaxProperty feet;

	@JsonProperty("kilometers")
	private MinMaxProperty kilometers;

	@JsonProperty("meters")
	private MinMaxProperty meters;

	@JsonProperty("miles")
	private MinMaxProperty miles;
}