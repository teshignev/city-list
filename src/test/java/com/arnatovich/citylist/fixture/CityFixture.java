package com.arnatovich.citylist.fixture;

import com.arnatovich.citylist.in.dto.CityDto;
import com.arnatovich.citylist.out.jpa.entity.CityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public class CityFixture {

  public static final Long ID = 7L;
  public static final String CITY_NAME = "Tokyo";
  public static final String INT_CITY_NAME = "Mumbai";
  public static final String INT_CITY_URL = "new_Mumbai_url";
  public static final String PHOTO_URL = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b2/Skyscrapers_of_Shinjuku_2009_January.jpg/500px-Skyscrapers_of_Shinjuku_2009_January.jpg";
  public static final int PAGE_NUMBER = 0;
  public static final int PAGE_SIZE = 10;

  public static CityDto cityDto() {
    return CityDto.builder()
        .id(ID)
        .cityName(CITY_NAME)
        .photoUrl(PHOTO_URL)
        .build();
  }

  public static CityEntity cityEntity() {
    return CityEntity.builder()
        .id(ID)
        .cityName(CITY_NAME)
        .photoUrl(PHOTO_URL)
        .build();
  }

  public static Optional<CityEntity> cityOptional() {
    return Optional.of(CityEntity.builder()
        .id(ID)
        .cityName(CITY_NAME)
        .photoUrl(PHOTO_URL)
        .build());
  }

  public static Optional<CityEntity> emptyCityOptional() {
    return Optional.empty();
  }

  public static Page<CityDto> pageOfCities(int pageNumber, int pageSize) {
    return Page.empty(PageRequest.of(pageNumber, pageSize));
  }

  public static Page<CityEntity> pageOfCitiEntities(int pageNumber, int pageSize) {
    return Page.empty(PageRequest.of(pageNumber, pageSize));
  }

  public static CityDto buildIntegratedCity() {
    return CityDto.builder()
        .id(3L)
        .cityName(INT_CITY_NAME)
        .photoUrl(INT_CITY_URL)
        .build();
  }
}
