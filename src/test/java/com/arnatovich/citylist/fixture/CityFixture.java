package com.arnatovich.citylist.fixture;

import com.arnatovich.citylist.in.dto.CityDto;
import com.arnatovich.citylist.out.jpa.entity.CityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class CityFixture {

  public static Long ID = 7L;
  public static String CITY_NAME = "Tokyo";
  public static String PHOTO_URL = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b2/Skyscrapers_of_Shinjuku_2009_January.jpg/500px-Skyscrapers_of_Shinjuku_2009_January.jpg";

  public static CityEntity cityEntity() {
    return CityEntity.builder()
        .id(ID)
        .cityName(CITY_NAME)
        .photoUrl(PHOTO_URL)
        .build();
  }
  public static Page<CityDto> pageOfCities(int pageNumber, int pageSize) {
    return Page.empty(PageRequest.of(pageNumber, pageSize));
  }

  public static Page<CityEntity> pageOfCitiEntities(int pageNumber, int pageSize) {
    return Page.empty(PageRequest.of(pageNumber, pageSize));
  }
}
