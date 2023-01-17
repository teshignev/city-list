package com.arnatovich.citylist.boot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.arnatovich.citylist.fixture.CityFixture;
import com.arnatovich.citylist.in.dto.CityDto;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = { CityListApp.class })
@DirtiesContext
public class CityListEndpointTests {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  private static PostgreSQLContainer postgres = new PostgreSQLContainer<>(ConfigurationProps.DB_IMAGE)
      .withDatabaseName(ConfigurationProps.DB_NAME)
      .withUsername(ConfigurationProps.DB_USER)
      .withPassword(ConfigurationProps.DB_PASSWORD)
      .withExposedPorts(ConfigurationProps.DB_PORT);

  @BeforeAll
  static void setup() {
    postgres.start();
    System.setProperty(ConfigurationProps.PROP_URL, postgres.getJdbcUrl());
  }

  @AfterAll
  static void cleanUp() {
    postgres.stop();
  }

  @Sql(scripts = {
      ConfigurationProps.SCHEMA_PATH,
      ConfigurationProps.DATA_PATH,
  })
  @Test
  public void retrieveCityListContent() {

    LinkedHashMap<String, Object> response = callEndpoint("/city-list");

    assertEquals(10, response.get("numberOfElements"));
    assertEquals(11, response.get("totalElements"));
    assertEquals(2, response.get("totalPages"));
    assertEquals(false, response.get("last"));
    assertEquals(true, response.get("first"));

    assertFirstCity(response);
  }

  @Test
  public void retrieveLastPage() {

    LinkedHashMap<String, Object> response = callEndpoint("/city-list?pageNumber=1");

    assertEquals(1, response.get("numberOfElements"));
    assertEquals(true, response.get("last"));
    assertEquals(false, response.get("first"));
  }

  @Test
  public void retrieveCitiesByName() {

    LinkedHashMap<String, Object> response = callEndpoint("/filtered-city-list?cityName=an");

    assertEquals(3, response.get("numberOfElements"));
  }

  @Test
  public void retrieveCitiesById() {

    CityDto response = restTemplate.getForObject(
      ConfigurationProps.LOCAL_HOST + port + "/city?id=3", CityDto.class);

    assertEquals(3, response.getId());
    assertEquals("Mumbai", response.getCityName());
    assertEquals("Mumbai_url", response.getPhotoUrl());
  }

  @Test
  public void updateCity() {

    CityDto city = CityFixture.buildIntegratedCity();

    CityDto oldCity = restTemplate.getForObject(
        ConfigurationProps.LOCAL_HOST + port + "/city?id=" + city.getId(), CityDto.class);

    restTemplate.put(buildUpdateCityUrl(city), city);

    CityDto updatedCity = restTemplate.getForObject(
        ConfigurationProps.LOCAL_HOST + port + "/city?id=" + city.getId(), CityDto.class);

    assertNotEquals(oldCity.getPhotoUrl(), updatedCity.getId());
    assertEquals(city.getId(), updatedCity.getId());
    assertEquals(city.getCityName(), updatedCity.getCityName());
    assertEquals(city.getPhotoUrl(), updatedCity.getPhotoUrl());
  }

  @NotNull
  private String buildUpdateCityUrl(CityDto city) {
    return ConfigurationProps.LOCAL_HOST + port
        + "/city?id=" + city.getId()
        + "&cityName=" + city.getCityName()
        + "&photoUrl=" + city.getPhotoUrl();
  }

  private LinkedHashMap<String, Object> callEndpoint(String endpoint) {
    return restTemplate.getForObject(
        ConfigurationProps.LOCAL_HOST + port + endpoint, LinkedHashMap.class);
  }

  private void assertFirstCity(LinkedHashMap<String, Object> response) {
    ArrayList<LinkedHashMap<String, Object>> content = (ArrayList) response.get("content");

    LinkedHashMap<String, Object> firstCity = content.get(0);
    assertEquals(10, firstCity.get("id"));
    assertEquals("Beijing", firstCity.get("cityName"));
    assertEquals("Beijing_url", firstCity.get("photoUrl"));
  }
}