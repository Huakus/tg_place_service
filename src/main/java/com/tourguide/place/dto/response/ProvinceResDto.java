package com.tourguide.place.dto.response;

import com.tourguide.place.dto.BaseDto;
import com.tourguide.place.enums.Country;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceResDto extends BaseDto {
    private String name;
    private Country country;
}
