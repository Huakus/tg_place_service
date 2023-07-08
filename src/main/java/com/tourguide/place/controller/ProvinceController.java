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

import com.tourguide.place.dto.request.ProvinceReqDto;
import com.tourguide.place.dto.response.ProvinceResDto;
import com.tourguide.place.service.ProvinceService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/v1/province")
@RequiredArgsConstructor
public class ProvinceController {
    private final ProvinceService provinceService;

    @GetMapping
    public ResponseEntity<List<ProvinceResDto>> getAllProvinces() {
        List<ProvinceResDto> provinces = provinceService.getAllProvinces();
        return new ResponseEntity<>(provinces, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ProvinceResDto> getProvinceByUuid(@PathVariable String uuid) {
        ProvinceResDto province = provinceService.getProvinceByUuid(uuid);
        return new ResponseEntity<>(province, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProvinceResDto> createProvince(@RequestBody ProvinceReqDto provinceRequest) {
        ProvinceResDto province = provinceService.createProvince(provinceRequest);
        return new ResponseEntity<>(province, HttpStatus.OK);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ProvinceResDto> getProvinceByUuid(@PathVariable String uuid, @RequestBody ProvinceReqDto provinceRequest) {
        ProvinceResDto province = provinceService.updateProvince(uuid, provinceRequest);
        return new ResponseEntity<>(province, HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<ProvinceResDto> deleteProvinceByUuid(@PathVariable String uuid) {
        provinceService.deleteProvinceByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
