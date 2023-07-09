package com.tourguide.place.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tourguide.place.dto.request.PlaceReqDto;
import com.tourguide.place.dto.response.PlaceResDto;
import com.tourguide.place.service.PlaceService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/v1/place")
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;

    @GetMapping
    public ResponseEntity<List<PlaceResDto>> getAllPlaces() {
        List<PlaceResDto> places = placeService.getAllPlaces();
        return new ResponseEntity<>(places, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<PlaceResDto> getPlaceByUuid(@PathVariable String uuid) {
        PlaceResDto place = placeService.getPlaceByUuid(uuid);
        return new ResponseEntity<>(place, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PlaceResDto> createPlace(@RequestBody PlaceReqDto placeRequest) {
        PlaceResDto place = placeService.createPlace(placeRequest);
        return new ResponseEntity<>(place, HttpStatus.OK);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<PlaceResDto> getPlaceByUuid(@PathVariable String uuid, @RequestBody PlaceReqDto placeRequest) {
        PlaceResDto place = placeService.updatePlace(uuid, placeRequest);
        return new ResponseEntity<>(place, HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<PlaceResDto> deletePlaceByUuid(@PathVariable String uuid) {
        placeService.deletePlaceByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
