package com.arnatovich.citylist.out.jpa.repository;

import com.arnatovich.citylist.out.jpa.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityListJpaRepository extends JpaRepository<CityEntity, Long> {
}
