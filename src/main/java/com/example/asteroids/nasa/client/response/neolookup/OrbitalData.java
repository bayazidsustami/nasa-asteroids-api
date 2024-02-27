package com.example.asteroids.nasa.client.response.neolookup;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrbitalData{

	@JsonProperty("orbital_data")
	private OrbitalData orbitalData;

	@JsonProperty("inclination")
	private String inclination;

	@JsonProperty("mean_anomaly")
	private String meanAnomaly;

	@JsonProperty("orbit_determination_date")
	private String orbitDeterminationDate;

	@JsonProperty("minimum_orbit_intersection")
	private String minimumOrbitIntersection;

	@JsonProperty("perihelion_argument")
	private String perihelionArgument;

	@JsonProperty("orbit_class")
	private OrbitClass orbitClass;

	@JsonProperty("last_observation_date")
	private String lastObservationDate;

	@JsonProperty("eccentricity")
	private String eccentricity;

	@JsonProperty("perihelion_distance")
	private String perihelionDistance;

	@JsonProperty("aphelion_distance")
	private String aphelionDistance;

	@JsonProperty("perihelion_time")
	private String perihelionTime;

	@JsonProperty("first_observation_date")
	private String firstObservationDate;

	@JsonProperty("orbit_id")
	private String orbitId;

	@JsonProperty("data_arc_in_days")
	private int dataArcInDays;

	@JsonProperty("jupiter_tisserand_invariant")
	private String jupiterTisserandInvariant;

	@JsonProperty("semi_major_axis")
	private String semiMajorAxis;

	@JsonProperty("orbital_period")
	private String orbitalPeriod;

	@JsonProperty("epoch_osculation")
	private String epochOsculation;

	@JsonProperty("orbit_uncertainty")
	private String orbitUncertainty;

	@JsonProperty("ascending_node_longitude")
	private String ascendingNodeLongitude;

	@JsonProperty("mean_motion")
	private String meanMotion;

	@JsonProperty("observations_used")
	private int observationsUsed;

	@JsonProperty("equinox")
	private String equinox;
}