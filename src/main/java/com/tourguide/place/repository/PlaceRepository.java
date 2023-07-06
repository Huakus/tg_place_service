package com.tourguide.place.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tourguide.place.model.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    
}
