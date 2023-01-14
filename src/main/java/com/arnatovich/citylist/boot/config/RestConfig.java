package com.arnatovich.citylist.boot.config;

import com.arnatovich.citylist.out.jpa.CityListRepository;
import com.arnatovich.citylist.in.rest.CityListRestService;
import com.arnatovich.citylist.in.rest.SpringCityListRestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfig {

  @Bean
  public CityListRestService cityListRestService(CityListRepository repository) {
    return new SpringCityListRestService(repository);
  }

}
