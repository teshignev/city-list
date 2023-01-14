package com.arnatovich.citylist.boot.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.arnatovich.citylist.out.jpa.CityListRepository;
import com.arnatovich.citylist.in.rest.CityListRestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RestConfigTest {

  @InjectMocks
  private RestConfig target;

  @Mock
  private CityListRepository repository;

  @Test
  void initRestConfig() {
    CityListRestService cityListRestService = target.cityListRestService(repository);

    assertNotNull(cityListRestService);
  }

}
