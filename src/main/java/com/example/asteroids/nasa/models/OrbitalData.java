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
public class OrbitalData {

    @JsonProperty("orbit_id")
    private String orbitId;

    @JsonProperty("first_observation_date")
    private String firstObservationDate;

    @JsonProperty("last_observation_date")
    private String lastObservationDate;

    @JsonProperty("orbit_class_type")
    private String orbitClassType;

    @JsonProperty("orbit_class_range")
    private String orbitClassRange;

    @JsonProperty("orbit_class_description")
    private String orbitClassDescription;

}
