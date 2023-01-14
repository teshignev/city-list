package com.arnatovich.citylist.out.jpa;

import com.arnatovich.citylist.in.dto.CityDto;
import com.arnatovich.citylist.out.jpa.mapper.CityDtoMapper;
import com.arnatovich.citylist.out.jpa.repository.CityListJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class CityListRepositoryImpl implements CityListRepository {

  private final CityListJpaRepository partRepository;

  @Override
  public Page<CityDto> findCities(Pageable pageable) {
    return partRepository.findAll(pageable).map(CityDtoMapper.INSTANCE::map);
  }
}
