package com.tourguide.place.dto;

import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class BaseDto {
    private String uuid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
