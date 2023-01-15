package com.arnatovich.citylist.out.jpa;

import com.arnatovich.citylist.in.dto.CityDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityListRepository {

  Page<CityDto> findCities(Pageable pageable);

  CityDto findCityById(Long id);

  CityDto updateCityById(Long id, String cityName, String photoUrl);
}
