package com.example.asteroids.nasa.client.response.neofeed;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CloseApproachDataItem{
	@JsonProperty("relative_velocity")
	private RelativeVelocity relativeVelocity;

	@JsonProperty("orbiting_body")
	private String orbitingBody;

	@JsonProperty("close_approach_date")
	private String closeApproachDate;

	@JsonProperty("epoch_date_close_approach")
	private long epochDateCloseApproach;

	@JsonProperty("close_approach_date_full")
	private String closeApproachDateFull;

	@JsonProperty("miss_distance")
	private MissDistance missDistance;
}