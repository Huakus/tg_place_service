package com.tourguide.place.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tourguide.place.dto.request.CityReqDto;
import com.tourguide.place.dto.response.CityResDto;
import com.tourguide.place.service.CityService;

public class CityControllerTest {

    @InjectMocks
    private CityController cityController;

    @Mock
    private CityService cityService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllCities() {
        CityResDto city = new CityResDto();
        when(cityService.getAllCities()).thenReturn(Arrays.asList(city));

        ResponseEntity<List<CityResDto>> response = cityController.getAllCities();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Arrays.asList(city), response.getBody());
    }

    @Test
    public void getCityByUuid() {
        String uuid = "test-uuid";
        CityResDto city = new CityResDto();
        when(cityService.getCityByUuid(uuid)).thenReturn(city);

        ResponseEntity<CityResDto> response = cityController.getCityByUuid(uuid);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(city, response.getBody());
    }

    @Test
    public void createCity() {
        CityReqDto request = new CityReqDto();
        CityResDto city = new CityResDto();
        when(cityService.createCity(request)).thenReturn(city);

        ResponseEntity<CityResDto> response = cityController.createCity(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(city, response.getBody());
    }

    @Test
    public void updateCity() {
        String uuid = "test-uuid";
        CityReqDto request = new CityReqDto();
        CityResDto city = new CityResDto();
        when(cityService.updateCity(eq(uuid), any(CityReqDto.class))).thenReturn(city);

        ResponseEntity<CityResDto> response = cityController.getCityByUuid(uuid, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(city, response.getBody());
    }

    @Test
    public void deleteCityByUuid() {
        String uuid = "test-uuid";

        ResponseEntity<CityResDto> response = cityController.deleteCityByUuid(uuid);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}

