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
public class TotalAsteroidResponse {

    @JsonProperty("total_asteroid")
    private int totalAsteroid;

    @JsonProperty("items")
    private List<AsteroidResponse> items;
}
