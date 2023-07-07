package com.tourguide.place.dto.request;

import com.tourguide.place.dto.BaseDto;
import com.tourguide.place.enums.Country;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProvinceReqDto extends BaseDto {
    private String name;
    private Country country;
}
