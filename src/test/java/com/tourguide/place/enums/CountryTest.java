package com.tourguide.place.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountryTest {
    @Test
    void testCountryValues() {
        assertEquals("Italy", Country.ITALY.getCountry());
        assertEquals("Argentina", Country.ARGENTINA.getCountry());
    }
}
