package com.tourguide.place.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tourguide.place.model.City;
import com.tourguide.place.model.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Optional<Place> findByUuid(String uuid);
    List<Place> findByNameAndCity(String name, City city);
}
