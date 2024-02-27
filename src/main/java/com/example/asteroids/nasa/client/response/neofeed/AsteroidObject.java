package com.example.asteroids.nasa.client.response.neofeed;

import java.util.List;

import com.example.asteroids.nasa.client.response.neolookup.OrbitalDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AsteroidObject{
	@JsonProperty("estimated_diameter")
	private EstimatedDiameter estimatedDiameter;

	@JsonProperty("neo_reference_id")
	private String neoReferenceId;

	@JsonProperty("nasa_jpl_url")
	private String nasaJplUrl;

	@JsonProperty("is_potentially_hazardous_asteroid")
	private boolean isPotentiallyHazardousAsteroid;

	@JsonProperty("is_sentry_object")
	private boolean isSentryObject;

	private String name;

	@JsonProperty("absolute_magnitude_h")
	private Object absoluteMagnitudeH;
	private Links links;
	private String id;

	@JsonProperty("close_approach_data")
	private List<CloseApproachDataItem> closeApproachData;

	@JsonProperty("orbital_data")
	private OrbitalDataResponse orbitalDataResponse;
}