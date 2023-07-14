package com.tourguide.place.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tourguide.place.dto.request.CityReqDto;
import com.tourguide.place.dto.response.CityResDto;
import com.tourguide.place.service.CityService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/v1/city")
// TODO: Make it general or remove it
@CrossOrigin(origins = {"http://localhost:3000"})
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;


    @GetMapping
    public ResponseEntity<List<CityResDto>> getAllCities() {
        List<CityResDto> citys = cityService.getAllCities();
        return new ResponseEntity<>(citys, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<CityResDto> getCityByUuid(@PathVariable String uuid) {
        CityResDto city = cityService.getCityByUuid(uuid);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CityResDto> createCity(@RequestBody CityReqDto cityRequest) {
        CityResDto city = cityService.createCity(cityRequest);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<CityResDto> getCityByUuid(@PathVariable String uuid, @RequestBody CityReqDto cityRequest) {
        CityResDto city = cityService.updateCity(uuid, cityRequest);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<CityResDto> deleteCityByUuid(@PathVariable String uuid) {
        cityService.deleteCityByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
