package com.tourguide.place.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tourguide.place.enums.Country;
import com.tourguide.place.model.Province;

public interface ProvinceRepository extends JpaRepository<Province, Long> {
    Optional<Province> findByUuid(String uuid);
    List<Province> findByNameAndCountry(String name, Country country);
}
