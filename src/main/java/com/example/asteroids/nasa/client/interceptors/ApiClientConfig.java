package com.example.asteroids.nasa.client.interceptors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class ApiClientConfig {

    public static String BASE_URL = "https://api.nasa.gov/neo/rest/v1/";

    @Value("${nasa.api-key}")
    private String apiKey;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(new AuthenticationInterceptor(apiKey)));
        return restTemplate;
    }
}
