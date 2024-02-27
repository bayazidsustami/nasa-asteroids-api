package com.example.asteroids.nasa.util;

import com.example.asteroids.nasa.models.AsteroidResponse;

import java.util.Comparator;

public class DistanceComparator implements Comparator<AsteroidResponse> {

    @Override
    public int compare(AsteroidResponse o1, AsteroidResponse o2) {
        return o1.getCloseApproachData().getDistanceToEarth().compareTo(
                o2.getCloseApproachData().getDistanceToEarth());
    }
}
