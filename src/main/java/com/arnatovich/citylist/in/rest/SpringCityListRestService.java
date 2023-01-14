package com.arnatovich.citylist.in.rest;

import com.arnatovich.citylist.in.dto.CityDto;
import com.arnatovich.citylist.out.jpa.CityListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
public class SpringCityListRestService implements CityListRestService {

  private final CityListRepository repository;

  @Override
  public ResponseEntity<Page<CityDto>> retrieveCities(Integer pageNumber, Integer pageSize) {

    Page<CityDto> cities = repository.findCities(PageRequest.of(pageNumber, pageSize));

    return new ResponseEntity<>(
        cities,
        HttpStatus.OK);
  }
}
