package com.example.asteroids.nasa.client.response.neolookup;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrbitClass{

	@JsonProperty("orbit_class_description")
	private String orbitClassDescription;

	@JsonProperty("orbit_class_range")
	private String orbitClassRange;

	@JsonProperty("orbit_class_type")
	private String orbitClassType;

}