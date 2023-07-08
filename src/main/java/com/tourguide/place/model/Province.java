package com.tourguide.place.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.tourguide.place.enums.Country;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Province extends BaseModel {
    @Column(nullable = false)
    private String name;

    @Column
    @NotNull(message = "Country cannot be null")
    @Enumerated(EnumType.STRING)
    private Country country;
}
