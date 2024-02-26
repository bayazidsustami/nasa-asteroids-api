package com.example.asteroids.nasa.client.response.neofeed;

import java.util.List;
import lombok.Data;

@Data
public class AsteroidObject{
	private EstimatedDiameter estimatedDiameter;
	private String neoReferenceId;
	private String nasaJplUrl;
	private boolean isPotentiallyHazardousAsteroid;
	private boolean isSentryObject;
	private String name;
	private Object absoluteMagnitudeH;
	private Links links;
	private String id;
	private List<CloseApproachDataItem> closeApproachData;
}