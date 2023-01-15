package com.arnatovich.citylist.in.exception;

import lombok.Getter;

import java.io.Serial;

@Getter
public class GenericRestServiceErrorException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 2002136992287246169L;
  private final int httpCode;

  public GenericRestServiceErrorException(final String message, final int httpCode) {
    super(message);
    this.httpCode = httpCode;
  }
}
