package com.example.asteroids.nasa.models;

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

    @JsonProperty("epoch_date")
    private long epochDate;

    @JsonProperty("relative_velocity")
    private BigDecimal relativeVelocity;

    @JsonProperty("distance_to_earth")
    private BigDecimal distanceToEarth;
}
