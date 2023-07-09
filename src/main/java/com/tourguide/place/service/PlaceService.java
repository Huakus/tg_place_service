package com.tourguide.place.service;

import java.util.List;

import com.tourguide.place.dto.request.PlaceReqDto;
import com.tourguide.place.dto.response.PlaceResDto;

public interface PlaceService {
    List<PlaceResDto> getAllPlaces();
    PlaceResDto getPlaceByUuid(String uuid);
    PlaceResDto createPlace(PlaceReqDto placeReqDto);
    PlaceResDto updatePlace(String uuid, PlaceReqDto placeReqDto);
    void deletePlaceByUuid(String uuid);
}
