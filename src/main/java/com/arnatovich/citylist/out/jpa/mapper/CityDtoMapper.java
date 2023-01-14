package com.arnatovich.citylist.out.jpa.mapper;

import com.arnatovich.citylist.in.dto.CityDto;
import com.arnatovich.citylist.out.jpa.entity.CityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface CityDtoMapper {

  CityDtoMapper INSTANCE = Mappers.getMapper(CityDtoMapper.class);

  CityDto map(CityEntity city);
}
