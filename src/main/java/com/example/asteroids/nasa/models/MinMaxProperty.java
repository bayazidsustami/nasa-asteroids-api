package com.example.asteroids.nasa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MinMaxProperty {

    @JsonProperty("estimated_diameter_max")
    private double estimatedDiameterMax;

    @JsonProperty("estimated_diameter_min")
    private double estimatedDiameterMin;

    private String unit;
}