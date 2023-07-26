package com.tourguide.place.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Country {
    ITALY("Italy"),
    ARGENTINA("Argentina"),
    GERMANY("Germany"),
    UNITED_STATES("United States");

    @Getter
    private final String country;
}
