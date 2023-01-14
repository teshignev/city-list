package com.arnatovich.citylist.in.rest;

import com.arnatovich.citylist.in.dto.CityDto;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
      @PositiveOrZero @RequestParam(required = false, defaultValue = DEFAULT_OFFSET) Integer offset,
      @Positive @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE)
      Integer pageSize);
}
