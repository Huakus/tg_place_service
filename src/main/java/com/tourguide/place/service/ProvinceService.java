package com.tourguide.place.service;

import java.util.List;

import com.tourguide.place.dto.request.ProvinceReqDto;
import com.tourguide.place.dto.response.ProvinceResDto;

public interface ProvinceService {
    List<ProvinceResDto> getAllProvinces();
    ProvinceResDto getProvinceByUuid(String uuid);
    ProvinceResDto createProvince(ProvinceReqDto provinceReqDto);
    ProvinceResDto updateProvince(String uuid, ProvinceReqDto provinceReqDto);
    void deleteProvinceByUuid(String uuid);
}
