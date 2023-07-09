package com.tourguide.place.dto.request;

import com.tourguide.place.dto.BaseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CityReqDto extends BaseDto {
    private String name;
    private String description;
    private Double latitude;
    private Double longitude;
    private String provinceUuid;
}
