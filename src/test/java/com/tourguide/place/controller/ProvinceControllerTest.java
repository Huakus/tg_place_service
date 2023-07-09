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

import com.tourguide.place.dto.request.ProvinceReqDto;
import com.tourguide.place.dto.response.ProvinceResDto;
import com.tourguide.place.service.ProvinceService;

public class ProvinceControllerTest {

    @InjectMocks
    private ProvinceController provinceController;

    @Mock
    private ProvinceService provinceService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllProvinces() {
        ProvinceResDto province = new ProvinceResDto();
        when(provinceService.getAllProvinces()).thenReturn(Arrays.asList(province));

        ResponseEntity<List<ProvinceResDto>> response = provinceController.getAllProvinces();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Arrays.asList(province), response.getBody());
    }

    @Test
    public void getProvinceByUuid() {
        String uuid = "test-uuid";
        ProvinceResDto province = new ProvinceResDto();
        when(provinceService.getProvinceByUuid(uuid)).thenReturn(province);

        ResponseEntity<ProvinceResDto> response = provinceController.getProvinceByUuid(uuid);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(province, response.getBody());
    }

    @Test
    public void createProvince() {
        ProvinceReqDto request = new ProvinceReqDto(); 
        ProvinceResDto province = new ProvinceResDto();
        when(provinceService.createProvince(request)).thenReturn(province);

        ResponseEntity<ProvinceResDto> response = provinceController.createProvince(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(province, response.getBody());
    }

    @Test
    public void updateProvince() {
        String uuid = "test-uuid";
        ProvinceReqDto request = new ProvinceReqDto();
        ProvinceResDto province = new ProvinceResDto();
        when(provinceService.updateProvince(eq(uuid), any(ProvinceReqDto.class))).thenReturn(province);

        ResponseEntity<ProvinceResDto> response = provinceController.getProvinceByUuid(uuid, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(province, response.getBody());
    }

    @Test
    public void deleteProvinceByUuid() {
        String uuid = "test-uuid";

        ResponseEntity<ProvinceResDto> response = provinceController.deleteProvinceByUuid(uuid);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
