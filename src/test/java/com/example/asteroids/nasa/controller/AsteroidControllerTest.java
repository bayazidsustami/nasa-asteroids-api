package com.example.asteroids.nasa.controller;

import com.example.asteroids.nasa.models.ApiResponse;
import com.example.asteroids.nasa.models.AsteroidResponse;
import com.example.asteroids.nasa.models.ErrorResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AsteroidControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testRequestEmptyDateRange() throws Exception {
        mockMvc.perform(
                get("/api/asteroids")
                        .queryParam("start_date", "")
                        .queryParam("end_date", "")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isBadRequest()
        ).andExpectAll( result -> {
            ErrorResponse response = objectMapper.readValue(
                    result.getResponse().getContentAsString(),
                    new TypeReference<>() {
                    }
            );
            Assertions.assertNotNull(response);
            Assertions.assertEquals("startDate and endDate is required", response.getMessage());
        });
    }

    @Test
    void testRequestOutOfRangeDate() throws Exception {
        mockMvc.perform(
                get("/api/asteroids")
                        .queryParam("start_date", "2024-02-15")
                        .queryParam("end_date", "2024-02-26")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isBadRequest()
        ).andExpectAll( result -> {
            ErrorResponse response = objectMapper.readValue(
                    result.getResponse().getContentAsString(),
                    new TypeReference<>() {
                    }
            );
            Assertions.assertNotNull(response);
            Assertions.assertEquals("maximum range is 7 days", response.getMessage());
        });
    }

    @Test
    void testRequestSuccess() throws Exception {
        mockMvc.perform(
                get("/api/asteroids")
                        .queryParam("start_date", "2024-02-15")
                        .queryParam("end_date", "2024-02-22")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andExpectAll( result -> {
            ApiResponse<List<AsteroidResponse>> response = objectMapper.readValue(
                    result.getResponse().getContentAsString(),
                    new TypeReference<>() {
                    }
            );

            Assertions.assertNotNull(response);
            Assertions.assertEquals("OK", response.getStatus());
            Assertions.assertEquals(10, response.getData().size());
        });
    }
}