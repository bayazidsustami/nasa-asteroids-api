package com.example.asteroids.nasa.service.mapper;

import com.example.asteroids.nasa.client.response.neofeed.AsteroidObject;
import com.example.asteroids.nasa.client.response.neofeed.CloseApproachDataItem;
import com.example.asteroids.nasa.client.response.neofeed.EstimatedDiameter;
import com.example.asteroids.nasa.client.response.neofeed.NeoFeedResponse;
import com.example.asteroids.nasa.client.response.neolookup.OrbitalDataResponse;
import com.example.asteroids.nasa.models.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AsteroidMapper {

    public List<AsteroidResponse> mapListToResponses(NeoFeedResponse neoFeedResponse) {
        List<AsteroidResponse> asteroidResponseList = new ArrayList<>();

        neoFeedResponse.getNearEarthObjects().forEach((k, items) -> {
            for (AsteroidObject asteroidObject : items) {
                EstimatedDiameter estimatedDiameter = asteroidObject.getEstimatedDiameter();
                CloseApproachDataItem closeApproachDataItem = asteroidObject.getCloseApproachData().get(0);

                AsteroidResponse asteroidRes = AsteroidResponse.builder()
                        .id(asteroidObject.getId())
                        .asteroidName(asteroidObject.getName())
                        .date(k)
                        .isHazardous(asteroidObject.isPotentiallyHazardousAsteroid())
                        .estimatedDiameter(setEstimatedDiameter(estimatedDiameter))
                        .closeApproachData(setCloseApproachData(closeApproachDataItem))
                        .build();

                asteroidResponseList.add(asteroidRes);
            }
        });

        return asteroidResponseList;
    }

    public DetailAsteroidResponse mapAsteroidDetailToResponse(AsteroidObject asteroidObject) {
        EstimatedDiameter estimatedDiameter = asteroidObject.getEstimatedDiameter();
        List<CloseApproachDataItem> closeApproachData = asteroidObject.getCloseApproachData();
        return DetailAsteroidResponse.builder()
                .id(asteroidObject.getId())
                .asteroidName(asteroidObject.getName())
                .isHazardous(asteroidObject.isPotentiallyHazardousAsteroid())
                .estimatedDiameter(setEstimatedDiameter(estimatedDiameter))
                .closeApproachDataList(closeApproachData.stream().map(this::setCloseApproachData).toList())
                .orbitalData(setOrbitalData(asteroidObject.getOrbitalDataResponse()))
                .build();
    }

    private OrbitalData setOrbitalData(OrbitalDataResponse orbitalDataResponse) {
        return OrbitalData.builder()
                .orbitId(orbitalDataResponse.getOrbitId())
                .firstObservationDate(orbitalDataResponse.getFirstObservationDate())
                .lastObservationDate(orbitalDataResponse.getLastObservationDate())
                .orbitClassType(orbitalDataResponse.getOrbitDataResponseClass().getOrbitClassType())
                .orbitClassRange(orbitalDataResponse.getOrbitDataResponseClass().getOrbitClassRange())
                .orbitClassDescription(orbitalDataResponse.getOrbitDataResponseClass().getOrbitClassDescription())
                .build();
    }

    private List<MinMaxProperty> setEstimatedDiameter(EstimatedDiameter estimatedDiameter) {
        List<MinMaxProperty> minMaxProperties = new ArrayList<>();
        minMaxProperties.add(MinMaxProperty.builder()
                .unit("meters")
                .estimatedDiameterMax(estimatedDiameter.getMeters().getEstimatedDiameterMax())
                .estimatedDiameterMin(estimatedDiameter.getMeters().getEstimatedDiameterMin())
                .build());
        minMaxProperties.add(MinMaxProperty.builder()
                .unit("kilometers")
                .estimatedDiameterMax(estimatedDiameter.getKilometers().getEstimatedDiameterMax())
                .estimatedDiameterMin(estimatedDiameter.getKilometers().getEstimatedDiameterMin())
                .build());
        minMaxProperties.add(MinMaxProperty.builder()
                .unit("miles")
                .estimatedDiameterMax(estimatedDiameter.getMiles().getEstimatedDiameterMax())
                .estimatedDiameterMin(estimatedDiameter.getMiles().getEstimatedDiameterMin())
                .build());
        minMaxProperties.add(MinMaxProperty.builder()
                .unit("feet")
                .estimatedDiameterMax(estimatedDiameter.getFeet().getEstimatedDiameterMax())
                .estimatedDiameterMin(estimatedDiameter.getFeet().getEstimatedDiameterMin())
                .build());
        return minMaxProperties;
    }

    private CloseApproachData setCloseApproachData(CloseApproachDataItem dataItem) {
        return CloseApproachData.builder()
                .relativeVelocity(dataItem.getRelativeVelocity().getKilometersPerHour() + " Km/H")
                .distanceToEarth(dataItem.getMissDistance().getKilometers() + " Km")
                .distance(new BigDecimal(dataItem.getMissDistance().getKilometers()))
                .epochDate(dataItem.getEpochDateCloseApproach())
                .dateTime(dataItem.getCloseApproachDateFull())
                .build();
    }

    public TotalAsteroidResponse mapListToResponseByDistance(NeoFeedResponse neoFeedResponse, String distance) {
        List<AsteroidResponse> asteroidResponseList = new ArrayList<>();

        neoFeedResponse.getNearEarthObjects().forEach((k, items) -> {
            for (AsteroidObject asteroidObject : items) {
                EstimatedDiameter estimatedDiameter = asteroidObject.getEstimatedDiameter();
                CloseApproachDataItem closeApproachDataItem = asteroidObject.getCloseApproachData().get(0);
                BigDecimal distanceRes = new BigDecimal(closeApproachDataItem.getMissDistance().getKilometers());
                BigDecimal distanceReq;
                try {
                    distanceReq = new BigDecimal(distance);
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid distance");
                }

                if (distanceReq.compareTo(distanceRes) < 0) {
                    AsteroidResponse asteroidRes = AsteroidResponse.builder()
                            .id(asteroidObject.getId())
                            .asteroidName(asteroidObject.getName())
                            .date(k)
                            .isHazardous(asteroidObject.isPotentiallyHazardousAsteroid())
                            .estimatedDiameter(setEstimatedDiameter(estimatedDiameter))
                            .closeApproachData(setCloseApproachData(closeApproachDataItem))
                            .build();

                    asteroidResponseList.add(asteroidRes);
                }
            }
        });

        return TotalAsteroidResponse.builder()
                .totalAsteroid(asteroidResponseList.size())
                .items(asteroidResponseList)
                .build();
    }
}
