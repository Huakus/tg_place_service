package com.tourguide.place.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.tourguide.place.enums.Country;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name", "country"}))
public class Province extends BaseModel {
    @NotNull(message = "Name cannot be null")
    private String name;

    @Column
    @NotNull(message = "Country cannot be null")
    @Enumerated(EnumType.STRING)
    private Country country;
}
