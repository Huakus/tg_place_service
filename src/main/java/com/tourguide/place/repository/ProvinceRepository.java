package com.tourguide.place.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tourguide.place.model.Province;

public interface ProvinceRepository extends JpaRepository<Province, Long> {
    
}
