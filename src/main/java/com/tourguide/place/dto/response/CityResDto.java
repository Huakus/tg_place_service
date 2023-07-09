package com.tourguide.place.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityResDto {
    private String name;
    private String description;
    private Double latitude;
    private Double longitude;
    private ProvinceResDto province;
}
