package com.example.asteroids.nasa.service.mapper;

import com.example.asteroids.nasa.client.response.neofeed.AsteroidObject;
import com.example.asteroids.nasa.client.response.neofeed.CloseApproachDataItem;
import com.example.asteroids.nasa.client.response.neofeed.EstimatedDiameter;
import com.example.asteroids.nasa.client.response.neofeed.NeoFeedResponse;
import com.example.asteroids.nasa.models.AsteroidResponse;
import com.example.asteroids.nasa.models.CloseApproachData;
import com.example.asteroids.nasa.models.DetailAsteroidResponse;
import com.example.asteroids.nasa.models.MinMaxProperty;

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
                .relativeVelocity(new BigDecimal(dataItem.getRelativeVelocity().getKilometersPerHour()))
                .distanceToEarth(new BigDecimal(dataItem.getMissDistance().getKilometers()))
                .epochDate(dataItem.getEpochDateCloseApproach())
                .build();
    }

}
