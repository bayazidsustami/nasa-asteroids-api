package com.example.asteroids.nasa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CloseApproachData {

    @JsonIgnore
    private long epochDate;

    @JsonProperty("date_time")
    private String dateTime;

    @JsonProperty("relative_velocity")
    private String relativeVelocity;

    @JsonProperty("distance_to_earth")
    private String distanceToEarth;

    @JsonIgnore
    private BigDecimal distance;
}
