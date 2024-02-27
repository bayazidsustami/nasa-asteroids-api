package com.example.asteroids.nasa.service.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public AsteroidMapper asteroidMapper() {
        return new AsteroidMapper();
    }

}
