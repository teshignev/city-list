package com.arnatovich.citylist.in.rest;

import com.arnatovich.citylist.in.dto.CityDto;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@RestController
@Validated
public interface CityListRestService {

  String DEFAULT_OFFSET = "0";
  String DEFAULT_PAGE_SIZE = "10";

  @GetMapping(path = "/city-list", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  ResponseEntity<Page<CityDto>> retrieveCities(
      @PositiveOrZero @RequestParam(required = false, defaultValue = DEFAULT_OFFSET) Integer pageNumber,
      @Positive @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize);

  @GetMapping(path = "/filtered-city-list", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  ResponseEntity<Page<CityDto>> retrieveCities(
      @NotBlank @RequestParam String cityName,
      @PositiveOrZero @RequestParam(required = false, defaultValue = DEFAULT_OFFSET) Integer pageNumber,
      @Positive @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize);

  @GetMapping(path = "/city", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  ResponseEntity<CityDto> retrieveCityById(
      @PositiveOrZero @RequestParam Long id);

  @PutMapping(path = "/city", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  ResponseEntity<CityDto> updateCityById(
      @PositiveOrZero @RequestParam Long id,
      @NotBlank @RequestParam String cityName,
      @NotBlank @RequestParam String photoUrl);
}
