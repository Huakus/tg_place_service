package com.tourguide.place.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Country {
    ITALY("italy"),
    ARGENTINA("argentina");

    @Getter
    private final String country;
}
