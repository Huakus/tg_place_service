package com.tourguide.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.tourguide.place.dto.request.ProvinceReqDto;
import com.tourguide.place.dto.response.ProvinceResDto;
import com.tourguide.place.exceptions.DoesntExistsException;
import com.tourguide.place.model.Province;
import com.tourguide.place.repository.ProvinceRepository;
import com.tourguide.place.service.impl.ProvinceServiceImpl;
import com.tourguide.place.enums.Country;

public class ProvinceServiceImplTest {

    @InjectMocks
    private ProvinceServiceImpl provinceService;

    @Mock
    private ProvinceRepository provinceRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllProvinces() {
        Province province1 = new Province();
        province1.setName("Test Province 1");
        province1.setCountry(Country.ITALY);

        Province province2 = new Province();
        province2.setName("Test Province 2");
        province2.setCountry(Country.ARGENTINA);

        ProvinceResDto provinceResDto1 = new ProvinceResDto();
        provinceResDto1.setName("Test Province 1");
        provinceResDto1.setCountry(Country.ITALY);

        ProvinceResDto provinceResDto2 = new ProvinceResDto();
        provinceResDto2.setName("Test Province 2");
        provinceResDto2.setCountry(Country.ARGENTINA);

        when(provinceRepository.findAll()).thenReturn(Arrays.asList(province1, province2));
        when(modelMapper.map(province1, ProvinceResDto.class)).thenReturn(provinceResDto1);
        when(modelMapper.map(province2, ProvinceResDto.class)).thenReturn(provinceResDto2);

        List<ProvinceResDto> result = provinceService.getAllProvinces();

        assertEquals(2, result.size());
        assertEquals(provinceResDto1, result.get(0));
        assertEquals(provinceResDto2, result.get(1));
    }

    @Test
    public void getProvinceByUuid() {
        String uuid = "test-uuid";
        Province province = new Province();
        province.setName("Test Province");
        province.setCountry(Country.ITALY);

        ProvinceResDto provinceResDto = new ProvinceResDto();
        provinceResDto.setName("Test Province");
        provinceResDto.setCountry(Country.ITALY);

        when(provinceRepository.findByUuid(uuid)).thenReturn(Optional.of(province));
        when(modelMapper.map(province, ProvinceResDto.class)).thenReturn(provinceResDto);

        ProvinceResDto result = provinceService.getProvinceByUuid(uuid);

        assertEquals(provinceResDto, result);
    }

    @Test
    public void getProvinceByUuid_notFound() {
        String uuid = "test-uuid";
        when(provinceRepository.findByUuid(uuid)).thenReturn(Optional.empty());

        assertThrows(DoesntExistsException.class, () -> provinceService.getProvinceByUuid(uuid));
    }

    @Test
    public void createProvince() {
        ProvinceReqDto request = new ProvinceReqDto();
        request.setName("Test Province");
        request.setCountry(Country.ITALY);

        Province province = new Province();
        province.setName("Test Province");
        province.setCountry(Country.ITALY);

        ProvinceResDto provinceResDto = new ProvinceResDto();
        provinceResDto.setName("Test Province");
        provinceResDto.setCountry(Country.ITALY);

        when(modelMapper.map(request, Province.class)).thenReturn(province);
        when(provinceRepository.save(province)).thenReturn(province);
        when(modelMapper.map(province, ProvinceResDto.class)).thenReturn(provinceResDto);

        ProvinceResDto result = provinceService.createProvince(request);

        assertEquals(provinceResDto, result);
    }

    @Test
    public void updateProvince() {
        String uuid = "test-uuid";
        ProvinceReqDto request = new ProvinceReqDto();
        request.setName("Updated Province");
        request.setCountry(Country.ARGENTINA);

        Province province = new Province();
        province.setName("Updated Province");
        province.setCountry(Country.ARGENTINA);

        ProvinceResDto provinceResDto = new ProvinceResDto();
        provinceResDto.setName("Updated Province");
        provinceResDto.setCountry(Country.ARGENTINA);

        when(provinceRepository.findByUuid(uuid)).thenReturn(Optional.of(province));
        when(provinceRepository.save(province)).thenReturn(province);
        when(modelMapper.map(province, ProvinceResDto.class)).thenReturn(provinceResDto);

        ProvinceResDto result = provinceService.updateProvince(uuid, request);

        assertEquals(provinceResDto, result);
    }

    @Test
    public void updateProvince_notFound() {
        String uuid = "test-uuid";
        ProvinceReqDto request = new ProvinceReqDto();
        when(provinceRepository.findByUuid(uuid)).thenReturn(Optional.empty());

        assertThrows(DoesntExistsException.class, () -> provinceService.updateProvince(uuid, request));
    }

    @Test
    public void deleteProvinceByUuid() {
        String uuid = "test-uuid";
        Province province = new Province();
        province.setName("Test Province");
        province.setCountry(Country.ITALY);
        when(provinceRepository.findByUuid(uuid)).thenReturn(Optional.of(province));

        provinceService.deleteProvinceByUuid(uuid);
    }

    @Test
    public void deleteProvinceByUuid_notFound() {
        String uuid = "test-uuid";
        when(provinceRepository.findByUuid(uuid)).thenReturn(Optional.empty());

        assertThrows(DoesntExistsException.class, () -> provinceService.deleteProvinceByUuid(uuid));
    }

}
