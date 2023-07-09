package com.tourguide.place.dto.request;

import com.tourguide.place.dto.BaseDto;
import com.tourguide.place.enums.Country;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProvinceReqDto extends BaseDto {
    private String name;
    private Country country;
}
