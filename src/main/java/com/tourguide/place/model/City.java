package com.tourguide.place.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false, nullable = false)
    private String uuid = UUID.randomUUID().toString();

    @Column(nullable = false)
    private String name;

    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    @Digits(integer = 3, fraction = 6)
    @Column(nullable = false, precision = 9, scale = 6)
    private Double latitude;

    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    @Digits(integer = 4, fraction = 6)
    @Column(nullable = false, precision = 10, scale = 6)
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "province_id", nullable = false)
    private Province province;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
