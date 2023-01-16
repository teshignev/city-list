package com.arnatovich.citylist.out.jpa.repository;

import static com.arnatovich.citylist.fixture.CityFixture.PAGE_NUMBER;
import static com.arnatovich.citylist.fixture.CityFixture.PAGE_SIZE;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.arnatovich.citylist.fixture.CityFixture;
import com.arnatovich.citylist.in.exception.RestNotFoundException;
import com.arnatovich.citylist.out.jpa.CityListRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
public class CityListRepositoryImplTest {

  @InjectMocks
  private CityListRepositoryImpl target;

  @Mock
  private CityListJpaRepository partRepository;

  @Test
  void returnAllCities() {

    when(partRepository.findAll(PageRequest.of(PAGE_NUMBER, PAGE_SIZE)))
        .thenReturn(CityFixture.pageOfCitiEntities(PAGE_NUMBER, PAGE_SIZE));

    target.findCities(PageRequest.of(PAGE_NUMBER, PAGE_SIZE));

    verify(partRepository).findAll(PageRequest.of(PAGE_NUMBER, PAGE_SIZE));
    verifyNoMoreInteractions(partRepository);
  }

  @Test
  void findCityById() {

    when(partRepository.findById(CityFixture.ID))
        .thenReturn(CityFixture.cityOptional());

    target.findCityById(CityFixture.ID);

    verify(partRepository).findById(CityFixture.ID);
    verifyNoMoreInteractions(partRepository);
  }

  @Test
  void notFindCityById() {

    when(partRepository.findById(CityFixture.ID))
        .thenReturn(CityFixture.emptyCityOptional());

    assertThrows(RestNotFoundException.class, () -> target.findCityById(CityFixture.ID));

    verify(partRepository).findById(CityFixture.ID);
    verifyNoMoreInteractions(partRepository);
  }

  @Test
  void updateCityById() {

    when(partRepository.updateCityById(CityFixture.ID, CityFixture.CITY_NAME, CityFixture.PHOTO_URL))
        .thenReturn(CityFixture.cityOptional());

    target.updateCityById(CityFixture.ID, CityFixture.CITY_NAME, CityFixture.PHOTO_URL);

    verify(partRepository).updateCityById(CityFixture.ID, CityFixture.CITY_NAME, CityFixture.PHOTO_URL);
    verifyNoMoreInteractions(partRepository);
  }

  @Test
  void notUpdateCityById() {

    when(partRepository.updateCityById(CityFixture.ID, CityFixture.CITY_NAME, CityFixture.PHOTO_URL))
        .thenReturn(CityFixture.emptyCityOptional());

    assertThrows(RestNotFoundException.class, () -> target.updateCityById(CityFixture.ID, CityFixture.CITY_NAME,
        CityFixture.PHOTO_URL));

    verify(partRepository).updateCityById(CityFixture.ID, CityFixture.CITY_NAME, CityFixture.PHOTO_URL);
    verifyNoMoreInteractions(partRepository);
  }

  @Test
  void findCitiesByName() {

    when(partRepository.findByCityNameContainingIgnoreCase(CityFixture.CITY_NAME, PageRequest.of(PAGE_NUMBER, PAGE_SIZE)))
        .thenReturn(CityFixture.pageOfCitiEntities(PAGE_NUMBER, PAGE_SIZE));

    target.findCitiesByName(CityFixture.CITY_NAME, PageRequest.of(PAGE_NUMBER, PAGE_SIZE));

    verify(partRepository).findByCityNameContainingIgnoreCase(CityFixture.CITY_NAME, PageRequest.of(PAGE_NUMBER, PAGE_SIZE));
    verifyNoMoreInteractions(partRepository);
  }
}
