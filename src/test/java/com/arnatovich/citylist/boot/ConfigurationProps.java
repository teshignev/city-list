package com.arnatovich.citylist.boot;

public interface ConfigurationProps {
  String DB_IMAGE = "postgres:13.0";
  String DB_NAME = "test";
  String DB_USER = "root";
  String DB_PASSWORD = "admin";
  int DB_PORT = 5432;

  String PROP_URL = "spring.datasource.url";
  String LOCAL_HOST = "http://localhost:";
  String SCHEMA_PATH = "classpath:schema.sql";
  String DATA_PATH = "classpath:data.sql";
}
