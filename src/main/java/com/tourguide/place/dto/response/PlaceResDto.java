package com.tourguide.place.dto.response;

import com.tourguide.place.dto.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaceResDto extends BaseDto {
    private String name;
    private String description;
    private Double latitude;
    private Double longitude;
    private CityResDto city;
}
