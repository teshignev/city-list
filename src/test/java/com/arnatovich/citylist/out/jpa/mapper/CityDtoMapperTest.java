package com.arnatovich.citylist.out.jpa.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.arnatovich.citylist.fixture.CityFixture;
import com.arnatovich.citylist.in.dto.CityDto;
import org.junit.jupiter.api.Test;

public class CityDtoMapperTest {

  @Test
  void mapEntityToDto() {

    CityDto cityDto = CityDtoMapper.INSTANCE.map(CityFixture.cityEntity());

    assertEquals(CityFixture.ID, cityDto.getId());
    assertEquals(CityFixture.CITY_NAME, cityDto.getCityName());
    assertEquals(CityFixture.PHOTO_URL, cityDto.getPhotoUrl());
  }
}
