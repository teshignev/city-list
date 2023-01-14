package com.arnatovich.citylist.in.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.arnatovich.citylist.fixture.CityFixture;
import com.arnatovich.citylist.in.dto.CityDto;
import com.arnatovich.citylist.out.jpa.CityListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    when(repository.findCities(PageRequest.of(pageNumber, pageSize)))
        .thenReturn(CityFixture.pageOfCities(pageNumber, pageSize));

    ResponseEntity<Page<CityDto>> pageResponseEntity = target.retrieveCities(pageNumber, pageSize);

    verify(repository).findCities(PageRequest.of(pageNumber, pageSize));
    verifyNoMoreInteractions(repository);
    assertEquals(HttpStatus.OK, pageResponseEntity.getStatusCode());
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
}
