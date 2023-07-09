package com.tourguide.place.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tourguide.place.model.City;
import com.tourguide.place.model.Province;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByUuid(String uuid);
    List<City> findByNameAndProvince(String name, Province province);
}
