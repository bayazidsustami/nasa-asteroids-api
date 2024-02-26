package com.example.asteroids.nasa.client.response.neofeed;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MinMaxProperty {

    @JsonProperty("estimated_diameter_max")
    private double estimatedDiameterMax;

    @JsonProperty("estimated_diameter_min")
    private double estimatedDiameterMin;
}
