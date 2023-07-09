package com.tourguide.place.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.tourguide.place.dto.request.CityReqDto;
import com.tourguide.place.dto.response.CityResDto;
import com.tourguide.place.exceptions.AlreadyExistsException;
import com.tourguide.place.exceptions.DoesntExistsException;
import com.tourguide.place.exceptions.InvalidDataException;
import com.tourguide.place.model.City;
import com.tourguide.place.repository.CityRepository;
import com.tourguide.place.repository.ProvinceRepository;
import com.tourguide.place.service.CityService;
import com.tourguide.place.util.MappingUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final ProvinceRepository provinceRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CityResDto> getAllCities() {
        var cities = cityRepository.findAll();

        return cities.stream()
            .map(city -> modelMapper.map(city, CityResDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public CityResDto getCityByUuid(String uuid) {
        var city = cityRepository.findByUuid(uuid)
            .orElseThrow(() -> new DoesntExistsException("City doesn't exist"));
        
            return modelMapper.map(city, CityResDto.class);
    }

    @Override
    public CityResDto createCity(CityReqDto cityReqDto) {
        var city = modelMapper.map(cityReqDto, City.class);
        city.initialize();
        setProvince(cityReqDto, city);

        try {
            cityRepository.save(city);
            return modelMapper.map(city, CityResDto.class);
        } catch (DataIntegrityViolationException exception) {
            throw new AlreadyExistsException("City already exists", exception);
        }
    }

    @Override
    public CityResDto updateCity(String uuid, CityReqDto cityReqDto) {
        var city = cityRepository.findByUuid(uuid)
            .orElseThrow(() -> new DoesntExistsException("City doesnt exists"));

        MappingUtil.copyNotNullProperties(cityReqDto, city);
        setProvince(cityReqDto, city);
        city.setUpdatedAt(LocalDateTime.now());

        try {
            cityRepository.save(city);
            return modelMapper.map(city, CityResDto.class);
        } catch (DataIntegrityViolationException exception) {
            throw new AlreadyExistsException("City already exists with this characteristics", exception);
        }
    }

    @Override
    public void deleteCityByUuid(String uuid) {
        var city = cityRepository.findByUuid(uuid)
            .orElseThrow(() -> new DoesntExistsException("City doesnt exists"));
        
        cityRepository.delete(city);
    }
    
    private void setProvince(CityReqDto cityReqDto, City city) {
    var province = provinceRepository.findByUuid(cityReqDto.getProvinceUuid())
        .orElseThrow(() -> new InvalidDataException("Invalid province_id"));
        
    city.setProvince(province);
    }
}
