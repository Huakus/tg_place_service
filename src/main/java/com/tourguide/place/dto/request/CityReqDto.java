package com.tourguide.place.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CityReqDto {
    private String name;
    private String description;
    private Double latitude;
    private Double longitude;
    private ProvinceReqDto province;
}
