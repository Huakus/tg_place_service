package com.tourguide.place.dto.response;

import com.tourguide.place.dto.BaseDto;
import com.tourguide.place.enums.Country;

import lombok.Builder;

@Builder
public class ProvinceResDto extends BaseDto {
    private String name;
    private Country country;
}
