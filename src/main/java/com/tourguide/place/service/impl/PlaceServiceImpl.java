package com.tourguide.place.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.tourguide.place.dto.request.PlaceReqDto;
import com.tourguide.place.dto.response.PlaceResDto;
import com.tourguide.place.exceptions.AlreadyExistsException;
import com.tourguide.place.exceptions.DoesntExistsException;
import com.tourguide.place.exceptions.InvalidDataException;
import com.tourguide.place.model.Place;
import com.tourguide.place.repository.CityRepository;
import com.tourguide.place.repository.PlaceRepository;
import com.tourguide.place.service.PlaceService;
import com.tourguide.place.util.MappingUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository placeRepository;
    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PlaceResDto> getAllPlaces() {
        var places = placeRepository.findAll();

        return places.stream()
            .map(city -> modelMapper.map(city, PlaceResDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public PlaceResDto getPlaceByUuid(String uuid) {
        var place = placeRepository.findByUuid(uuid)
            .orElseThrow(() -> new DoesntExistsException("Place doesn't exist"));
        
        return modelMapper.map(place, PlaceResDto.class);
    }

    @Override
    public PlaceResDto createPlace(PlaceReqDto placeReqDto) {
        var place = modelMapper.map(placeReqDto, Place.class);
        place.initialize();
        setCity(place, placeReqDto.getCityUuid());

        try {
            placeRepository.save(place);
            return modelMapper.map(place, PlaceResDto.class);
        } catch (DataIntegrityViolationException exception) {
            if(exception.getCause().getClass().equals(ConstraintViolationException.class)) {
                throw new AlreadyExistsException("Place already exists", exception);
            }

            throw exception;
        }
    }

    @Override
    public PlaceResDto updatePlace(String uuid, PlaceReqDto placeReqDto) {
        var place = placeRepository.findByUuid(uuid)
            .orElseThrow(() -> new DoesntExistsException("Place doesn't exist"));
        
        MappingUtil.copyNotNullProperties(placeReqDto, place);

        setCity(place, placeReqDto.getCityUuid());
        place.setUpdatedAt(LocalDateTime.now());

        try {
            placeRepository.save(place);
            return modelMapper.map(place, PlaceResDto.class);
        } catch (DataIntegrityViolationException exception) {
            throw new AlreadyExistsException("Place already exists", exception);
        }
    }

    @Override
    public void deletePlaceByUuid(String uuid) {
        var place = placeRepository.findByUuid(uuid)
            .orElseThrow(() -> new DoesntExistsException("Place doesn't exist"));
    
        placeRepository.delete(place);
    }
    
    private void setCity(Place place, String cityUuid) {
        var city = cityRepository.findByUuid(cityUuid)
            .orElseThrow(() -> new InvalidDataException("Invalid city_uuid"));
            
        place.setCity(city);
    }
}
