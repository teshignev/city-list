package com.arnatovich.citylist.in.rest;

import static com.arnatovich.citylist.in.rest.SpringCityListRestService.SORTING_NAME;
import static com.arnatovich.citylist.out.jpa.CityListRepositoryImpl.CITY_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.arnatovich.citylist.fixture.CityFixture;
import com.arnatovich.citylist.in.dto.CityDto;
import com.arnatovich.citylist.in.exception.RestNotFoundException;
import com.arnatovich.citylist.out.jpa.CityListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class SpringCityListRestServiceTest {

  @InjectMocks
  private SpringCityListRestService target;

  @Mock
  private CityListRepository repository;

  @Test
  void retrieveCities() {
    int pageNumber = 0;
    int pageSize = 3;

    when(repository.findCities(PageRequest.of(pageNumber, pageSize, Sort.by(SORTING_NAME).ascending())))
        .thenReturn(CityFixture.pageOfCities(pageNumber, pageSize));

    ResponseEntity<Page<CityDto>> response = target.retrieveCities(pageNumber, pageSize);

    verify(repository).findCities(PageRequest.of(pageNumber, pageSize, Sort.by(SORTING_NAME).ascending()));
    verifyNoMoreInteractions(repository);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void pageSizeZero() {
    int pageNumber = 0;
    int pageSize = 0;

    assertThrows(IllegalArgumentException.class, () -> target.retrieveCities(pageNumber, pageSize));

    verifyNoInteractions(repository);
  }

  @Test
  void pageSizeNegative() {
    int pageNumber = 0;
    int pageSize = -10;

    assertThrows(IllegalArgumentException.class, () -> target.retrieveCities(pageNumber, pageSize));

    verifyNoInteractions(repository);
  }

  @Test
  void pageNumberNegative() {
    int pageNumber = -1;
    int pageSize = 10;

    assertThrows(IllegalArgumentException.class, () -> target.retrieveCities(pageNumber, pageSize));

    verifyNoInteractions(repository);
  }

  @Test
  void retrieveCityById() {
    when(repository.findCityById(CityFixture.ID))
        .thenReturn(CityFixture.cityDto());

    ResponseEntity<CityDto> response = target.retrieveCityById(CityFixture.ID);

    verify(repository).findCityById(CityFixture.ID);
    verifyNoMoreInteractions(repository);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void notFoundCityById() {
    when(repository.findCityById(CityFixture.ID))
        .thenThrow(new RestNotFoundException(CITY_NOT_FOUND));

    assertThrows(RestNotFoundException.class, () -> target.retrieveCityById(CityFixture.ID));

    verify(repository).findCityById(CityFixture.ID);
    verifyNoMoreInteractions(repository);
  }

  @Test
  void updateCityById() {
    when(repository.updateCityById(CityFixture.ID, CityFixture.CITY_NAME, CityFixture.PHOTO_URL))
        .thenReturn(CityFixture.cityDto());

    ResponseEntity<CityDto> response = target.updateCityById(CityFixture.ID, CityFixture.CITY_NAME, CityFixture.PHOTO_URL);

    verify(repository).updateCityById(CityFixture.ID, CityFixture.CITY_NAME, CityFixture.PHOTO_URL);
    verifyNoMoreInteractions(repository);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void notUpdateCityById() {
    when(repository.updateCityById(CityFixture.ID, CityFixture.CITY_NAME, CityFixture.PHOTO_URL))
        .thenThrow(new RestNotFoundException(CITY_NOT_FOUND));

    assertThrows(RestNotFoundException.class, () -> target.updateCityById(CityFixture.ID, CityFixture.CITY_NAME,
        CityFixture.PHOTO_URL));

    verify(repository).updateCityById(CityFixture.ID, CityFixture.CITY_NAME, CityFixture.PHOTO_URL);
    verifyNoMoreInteractions(repository);
  }

  @Test
  void retrieveCitiesByName() {
    int pageNumber = 0;
    int pageSize = 3;

    when(repository.findCitiesByName(CityFixture.CITY_NAME, PageRequest.of(pageNumber, pageSize, Sort.by(SORTING_NAME).ascending())))
        .thenReturn(CityFixture.pageOfCities(pageNumber, pageSize));

    ResponseEntity<Page<CityDto>> response = target.retrieveCities(CityFixture.CITY_NAME, pageNumber, pageSize);

    verify(repository).findCitiesByName(CityFixture.CITY_NAME, PageRequest.of(pageNumber, pageSize, Sort.by(SORTING_NAME).ascending()));
    verifyNoMoreInteractions(repository);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
}
