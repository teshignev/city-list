package com.arnatovich.citylist.out.jpa;

import com.arnatovich.citylist.in.dto.CityDto;
import com.arnatovich.citylist.in.exception.RestNotFoundException;
import com.arnatovich.citylist.out.jpa.entity.CityEntity;
import com.arnatovich.citylist.out.jpa.mapper.CityDtoMapper;
import com.arnatovich.citylist.out.jpa.repository.CityListJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class CityListRepositoryImpl implements CityListRepository {

  public static final String CITY_NOT_FOUND = "City not found ;(";
  private final CityListJpaRepository partRepository;

  @Override
  public Page<CityDto> findCities(Pageable pageable) {
    return partRepository.findAll(pageable).map(CityDtoMapper.INSTANCE::map);
  }

  @Override
  public CityDto findCityById(Long id) {
    CityEntity cityEntity = partRepository.findById(id)
        .orElseThrow(() -> new RestNotFoundException(CITY_NOT_FOUND));

    return CityDtoMapper.INSTANCE.map(cityEntity);
  }

  @Override
  public CityDto updateCityById(Long id, String cityName, String photoUrl) {
    CityEntity cityEntity = partRepository.updateCityById(id, cityName, photoUrl)
        .orElseThrow(() -> new RestNotFoundException(CITY_NOT_FOUND));

    return CityDtoMapper.INSTANCE.map(cityEntity);
  }

  @Override
  public Page<CityDto> findCitiesByName(String cityName, Pageable pageable) {

    return partRepository.findByCityNameContainingIgnoreCase(cityName, pageable)
        .map(CityDtoMapper.INSTANCE::map);
  }
}
