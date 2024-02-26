package com.example.asteroids.nasa.client.response.neofeed;

import lombok.Data;

@Data
public class RelativeVelocity{
	private String kilometersPerHour;
	private String kilometersPerSecond;
	private String milesPerHour;
}