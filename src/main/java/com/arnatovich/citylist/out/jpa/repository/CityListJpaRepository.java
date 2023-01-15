package com.arnatovich.citylist.out.jpa.repository;

import com.arnatovich.citylist.out.jpa.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CityListJpaRepository extends JpaRepository<CityEntity, Long> {

  @Query(value =
      "UPDATE city_list"
          + " SET city_name = :cityName, photo_url = :photoUrl"
          + " WHERE id = :id"
          + " RETURNING id, city_name, photo_url",
      nativeQuery = true)
  Optional<CityEntity> updateCityById(
      @Param("id") Long id,
      @Param("cityName") String cityName,
      @Param("photoUrl") String photoUrl);
}
