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

import com.tourguide.place.dto.request.PlaceReqDto;
import com.tourguide.place.dto.response.PlaceResDto;
import com.tourguide.place.service.PlaceService;

public class PlaceControllerTest {

    @InjectMocks
    private PlaceController placeController;

    @Mock
    private PlaceService placeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllPlaces() {
        PlaceResDto place = new PlaceResDto(); // asumirás que PlaceResDto tiene un constructor sin argumentos
        when(placeService.getAllPlaces()).thenReturn(Arrays.asList(place));

        ResponseEntity<List<PlaceResDto>> response = placeController.getAllPlaces();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Arrays.asList(place), response.getBody());
    }

    @Test
    public void getPlaceByUuid() {
        String uuid = "test-uuid";
        PlaceResDto place = new PlaceResDto();
        when(placeService.getPlaceByUuid(uuid)).thenReturn(place);

        ResponseEntity<PlaceResDto> response = placeController.getPlaceByUuid(uuid);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(place, response.getBody());
    }

    @Test
    public void createPlace() {
        PlaceReqDto request = new PlaceReqDto();
        PlaceResDto place = new PlaceResDto();
        when(placeService.createPlace(request)).thenReturn(place);

        ResponseEntity<PlaceResDto> response = placeController.createPlace(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(place, response.getBody());
    }

    @Test
    public void updatePlace() {
        String uuid = "test-uuid";
        PlaceReqDto request = new PlaceReqDto();
        PlaceResDto place = new PlaceResDto();
        when(placeService.updatePlace(eq(uuid), any(PlaceReqDto.class))).thenReturn(place);

        ResponseEntity<PlaceResDto> response = placeController.getPlaceByUuid(uuid, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(place, response.getBody());
    }

    @Test
    public void deletePlaceByUuid() {
        String uuid = "test-uuid";

        ResponseEntity<PlaceResDto> response = placeController.deletePlaceByUuid(uuid);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}

