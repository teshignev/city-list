package com.arnatovich.citylist.in.rest;

import com.arnatovich.citylist.in.dto.CityDto;
import com.arnatovich.citylist.out.jpa.CityListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
public class SpringCityListRestService implements CityListRestService {

  public static final String SORTING_ID = "id";

  private final CityListRepository repository;

  @Override
  public ResponseEntity<Page<CityDto>> retrieveCities(Integer pageNumber, Integer pageSize) {

    Page<CityDto> cities = repository.findCities(PageRequest.of(pageNumber, pageSize, Sort.by(SORTING_ID).ascending()));

    return new ResponseEntity<>(
        cities,
        HttpStatus.OK);
  }

  @Override
  public ResponseEntity<CityDto> retrieveCityById(Long id) {
    CityDto city = repository.findCityById(id);

    return new ResponseEntity<>(
        city,
        HttpStatus.OK);
  }

  @Override
  public ResponseEntity<CityDto> updateCityById(Long id, String cityName, String photoUrl) {
    CityDto city = repository.updateCityById(id, cityName, photoUrl);

    return new ResponseEntity<>(
        city,
        HttpStatus.OK);
  }
}
