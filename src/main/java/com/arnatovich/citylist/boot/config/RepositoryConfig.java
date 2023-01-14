package com.arnatovich.citylist.boot.config;

import com.arnatovich.citylist.out.jpa.CityListRepository;
import com.arnatovich.citylist.out.jpa.CityListRepositoryImpl;
import com.arnatovich.citylist.out.jpa.repository.CityListJpaRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.arnatovich.citylist.out.jpa.repository")
@EntityScan("com.arnatovich.citylist.out.jpa.entity")
public class RepositoryConfig {

  @Bean
  public CityListRepository cityListRepository(CityListJpaRepository partRepository) {
    return new CityListRepositoryImpl(partRepository);
  }

}
