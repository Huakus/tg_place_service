package com.tourguide.place.dto.request;

import com.tourguide.place.dto.BaseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CityReqDto extends BaseDto {
    private String name;
    private String description;
    private Double latitude;
    private Double longitude;
    private String provinceUuid;
}
