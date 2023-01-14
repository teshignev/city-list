package com.arnatovich.citylist.in.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityDto {

  @JsonIgnore
  private Long id;

  private String cityName;
  private String photoUrl;
}
