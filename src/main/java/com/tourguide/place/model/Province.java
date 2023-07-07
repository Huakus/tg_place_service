package com.tourguide.place.model;

import com.tourguide.place.enums.Country;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
public class Province extends BaseModel {
    @Column(nullable = false)
    private String name;

    @Column
    @NotNull(message = "Country cannot be null")
    @Enumerated(EnumType.STRING)
    private Country country;
}
