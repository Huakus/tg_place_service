package com.tourguide.place.service;

import java.util.List;

import com.tourguide.place.dto.request.CityReqDto;
import com.tourguide.place.dto.response.CityResDto;

public interface CityService {
    List<CityResDto> getAllCities();
    CityResDto getCityByUuid(String uuid);
    CityResDto createCity(CityReqDto provinceReqDto);
    CityResDto updateCity(String uuid, CityReqDto provinceReqDto);
    void deleteCityByUuid(String uuid);
}
