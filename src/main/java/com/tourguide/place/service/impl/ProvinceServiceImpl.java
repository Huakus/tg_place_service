package com.tourguide.place.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;

import com.tourguide.place.dto.request.ProvinceReqDto;
import com.tourguide.place.dto.response.ProvinceResDto;
import com.tourguide.place.exceptions.AlreadyExistsException;
import com.tourguide.place.exceptions.DoesntExistsException;
import com.tourguide.place.model.Province;
import com.tourguide.place.repository.ProvinceRepository;
import com.tourguide.place.service.ProvinceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProvinceServiceImpl implements ProvinceService {
    private final ProvinceRepository provinceRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ProvinceResDto> getAllProvinces() {
        List<Province> provinces = provinceRepository.findAll();

        return provinces.stream()
                .map(province -> modelMapper.map(province, ProvinceResDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProvinceResDto getProvinceByUuid(String uuid) {
        Province province = provinceRepository.findByUuid(uuid);
        if(province == null) {
            throw new DoesntExistsException("Province doesn't exists");
        }
        return modelMapper.map(province, ProvinceResDto.class);
    }

    @Override
    public ProvinceResDto createProvince(ProvinceReqDto provinceReqDto) {
        Province newProvince = modelMapper.map(provinceReqDto, Province.class);
        if(provinceRepository.exists(Example.of(newProvince))) {
            throw new AlreadyExistsException("Province already exists");
        }

        LocalDateTime now = LocalDateTime.now();
        newProvince.setUuid(UUID.randomUUID().toString());
        newProvince.setCreatedAt(now);
        newProvince.setUpdatedAt(now);
        
        Province savedProvince = provinceRepository.save(newProvince);
        return modelMapper.map(savedProvince, ProvinceResDto.class);
    }

    @Override
    public ProvinceResDto updateProvince(String uuid, ProvinceReqDto provinceReqDto) {
        Province province = provinceRepository.findByUuid(uuid);
        if(province == null) {
            throw new DoesntExistsException("Province doesn't exists");
        }

        if(isEqual(province, provinceReqDto)) {
            throw new DoesntExistsException("There are no changes to be applied");
        }

        Province newProvince = new Province();
        newProvince.setName(provinceReqDto.getName() != null ? provinceReqDto.getName() : province.getName());
        newProvince.setCountry(provinceReqDto.getCountry() != null ? provinceReqDto.getCountry() : province.getCountry());

        if(provinceRepository.exists(Example.of(newProvince))) {
            throw new AlreadyExistsException("Province already exists with this characteristics");
        }

        updateModelFromDto(province, provinceReqDto);

        Province savedProvince = provinceRepository.save(province);
        return modelMapper.map(savedProvince, ProvinceResDto.class);
    }

    @Override
    public void deleteProvinceByUuid(String uuid) {
        Province province = provinceRepository.findByUuid(uuid);
        provinceRepository.delete(province);
    }

    private void updateModelFromDto(Province model, ProvinceReqDto dto) {
        modelMapper.map(dto, model);
        model.setUpdatedAt(LocalDateTime.now());
    }

    private boolean isEqual(Province model, ProvinceReqDto dto) {
        return Objects.equals(model.getCountry(), dto.getCountry())
            && Objects.equals(model.getName(), dto.getName());
    }
}
