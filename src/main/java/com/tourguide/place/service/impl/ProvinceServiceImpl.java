package com.tourguide.place.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguide.place.dto.request.ProvinceReqDto;
import com.tourguide.place.dto.response.ProvinceResDto;
import com.tourguide.place.model.Province;
import com.tourguide.place.repository.ProvinceRepository;
import com.tourguide.place.service.ProvinceService;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    private final ProvinceRepository provinceRepository;

    @Autowired
    public ProvinceServiceImpl(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @Override
    public List<ProvinceResDto> getAllProvinces() {
        List<Province> provinces = provinceRepository.findAll();

        return provinces.stream()
                .map(this::getDtoFromModel)
                .collect(Collectors.toList());
    }

    @Override
    public ProvinceResDto getProvinceByUuid(String uuid) {
        Province province = provinceRepository.findByUuid(uuid);
        return getDtoFromModel(province);
    }

    @Override
    public ProvinceResDto createProvince(ProvinceReqDto provinceReqDto) {
        Province newProvince = getModelFromDto(provinceReqDto);
        Province savedProvince = provinceRepository.save(newProvince);
        return getDtoFromModel(savedProvince);
    }

    @Override
    public ProvinceResDto updateProvince(String uuid, ProvinceReqDto provinceReqDto) {
        Province province = provinceRepository.findByUuid(uuid);
        updateModelFromDto(province, provinceReqDto);
        Province savedProvince = provinceRepository.save(province);
        return getDtoFromModel(savedProvince);
    }

    @Override
    public void deleteProvinceByUuid(String uuid) {
        Province province = provinceRepository.findByUuid(uuid);
        provinceRepository.delete(province);
    }
    
    private ProvinceResDto getDtoFromModel(Province model) {
        return ProvinceResDto.builder()
            .name(model.getName())
            .country(model.getCountry())
            .build();
    }

    private Province getModelFromDto(ProvinceReqDto dto) {
        return Province.builder()
            .name(dto.getName())
            .country(dto.getCountry())
            .build();
    }

    private void updateModelFromDto(Province model, ProvinceReqDto dto) {
        model.setName(dto.getName() != null ? dto.getName() : model.getName());
        model.setCountry(dto.getCountry() != null ? dto.getCountry() : model.getCountry());
    }
}
