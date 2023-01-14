package com.arnatovich.citylist.boot.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.arnatovich.citylist.out.jpa.CityListRepository;
import com.arnatovich.citylist.out.jpa.repository.CityListJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RepositoryConfigTest {

  @InjectMocks
  private RepositoryConfig target;

  @Mock
  private CityListJpaRepository partRepository;

  @Test
  void initCityListRepository() {
    CityListRepository cityListRepository = target.cityListRepository(partRepository);

    assertNotNull(cityListRepository);
  }

}
