package com.arnatovich.citylist.out.jpa.repository;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.arnatovich.citylist.fixture.CityFixture;
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
    int pageNumber = 0;
    int pageSize = 10;

    when(partRepository.findAll(PageRequest.of(pageNumber, pageSize)))
        .thenReturn(CityFixture.pageOfCitiEntities(pageNumber, pageSize));

    target.findCities(PageRequest.of(pageNumber, pageSize));

    verify(partRepository).findAll(PageRequest.of(pageNumber, pageSize));
    verifyNoMoreInteractions(partRepository);
  }
}
