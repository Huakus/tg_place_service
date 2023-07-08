package com.tourguide.place.service.impl;

import java.beans.PropertyDescriptor;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

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
        Province province = provinceRepository.findByUuid(uuid)
            .orElseThrow(() -> new DoesntExistsException("Province doesn't exists"));
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

    public ProvinceResDto updateProvince(String uuid, ProvinceReqDto provinceReqDto) {
        Province province = provinceRepository.findByUuid(uuid)
            .orElseThrow(() -> new DoesntExistsException("Province doesn't exists"));

        if(isEqual(province, provinceReqDto)) {
            throw new DoesntExistsException("There are no changes to be applied");
        }

        BeanUtils.copyProperties(provinceReqDto, province, getNullPropertyNames(provinceReqDto));
        province.setUpdatedAt(LocalDateTime.now());
        try {
            Province savedProvince = provinceRepository.save(province);
            return modelMapper.map(savedProvince, ProvinceResDto.class);
        } catch (DataIntegrityViolationException exception) {
            throw new AlreadyExistsException("Province already exists with this characteristics", exception);
        }
    }


    /*
    @Override
    public ProvinceResDto updateProvince(String uuid, ProvinceReqDto provinceReqDto) {
        Province province = provinceRepository.findByUuid(uuid)
            .orElseThrow(() -> new DoesntExistsException("Province doesn't exists"));

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

    */

    @Override
    public void deleteProvinceByUuid(String uuid) {
        Province province = provinceRepository.findByUuid(uuid)
            .orElseThrow(() -> new DoesntExistsException("Province doesn't exists"));
        provinceRepository.delete(province);
    }

    /*
    private void updateModelFromDto(Province model, ProvinceReqDto dto) {
        modelMapper.map(dto, model);
        model.setUpdatedAt(LocalDateTime.now());
    }

    */

    private boolean isEqual(Province model, ProvinceReqDto dto) {
        return Objects.equals(model.getCountry(), dto.getCountry())
            && Objects.equals(model.getName(), dto.getName());
    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<String>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}
