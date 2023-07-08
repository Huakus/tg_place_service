package com.tourguide.place.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BaseDto {
    private String uuid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
