package com.arnatovich.citylist.in.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class RestNotFoundException extends GenericRestServiceErrorException {

  @Serial
  private static final long serialVersionUID = 807608151874276760L;

  public RestNotFoundException(String message) {
    super(message, HttpStatus.NOT_FOUND.value());
  }
}
