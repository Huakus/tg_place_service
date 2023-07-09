package com.tourguide.place.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.tourguide.place.dto.request.ProvinceReqDto;
import com.tourguide.place.dto.response.ProvinceResDto;
import com.tourguide.place.exceptions.InvalidDataException;
import com.tourguide.place.exceptions.DoesntExistsException;
import com.tourguide.place.model.Province;
import com.tourguide.place.repository.ProvinceRepository;
import com.tourguide.place.service.ProvinceService;
import com.tourguide.place.util.MappingUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProvinceServiceImpl implements ProvinceService {
    private final ProvinceRepository provinceRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ProvinceResDto> getAllProvinces() {
        var provinces = provinceRepository.findAll();

        return provinces.stream()
                .map(province -> modelMapper.map(province, ProvinceResDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProvinceResDto getProvinceByUuid(String uuid) {
        var province = provinceRepository.findByUuid(uuid)
            .orElseThrow(() -> new DoesntExistsException("Province doesn't exists"));
        return modelMapper.map(province, ProvinceResDto.class);
    }

    @Override
    public ProvinceResDto createProvince(ProvinceReqDto provinceReqDto) {
        var newProvince = modelMapper.map(provinceReqDto, Province.class);
        newProvince.initialize();

        try {
            var savedProvince = provinceRepository.save(newProvince);
            return modelMapper.map(savedProvince, ProvinceResDto.class);
        } catch (DataIntegrityViolationException exception) {
            throw new InvalidDataException("Province already exists", exception);
        }
    }

    public ProvinceResDto updateProvince(String uuid, ProvinceReqDto provinceReqDto) {
        var province = provinceRepository.findByUuid(uuid)
            .orElseThrow(() -> new DoesntExistsException("Province doesn't exists"));

        MappingUtil.copyNotNullProperties(provinceReqDto, province);
        province.setUpdatedAt(LocalDateTime.now());
        
        try {
            var savedProvince = provinceRepository.save(province);
            return modelMapper.map(savedProvince, ProvinceResDto.class);
        } catch (DataIntegrityViolationException exception) {
            throw new InvalidDataException("Province already exists with this characteristics", exception);
        }
    }

    @Override
    public void deleteProvinceByUuid(String uuid) {
        var province = provinceRepository.findByUuid(uuid)
            .orElseThrow(() -> new DoesntExistsException("Province doesn't exists"));
        provinceRepository.delete(province);
    }
}
