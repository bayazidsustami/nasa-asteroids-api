package com.example.asteroids.nasa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AsteroidResponse {

    private String id;

    @JsonProperty("asteroid_name")
    private String asteroidName;

    private String date;

    @JsonProperty("estimated_diameter")
    private List<MinMaxProperty> estimatedDiameter;

    @JsonProperty("is_hazardous")
    private boolean isHazardous;

    @JsonProperty("close_approach_data")
    private CloseApproachData closeApproachData;

}
