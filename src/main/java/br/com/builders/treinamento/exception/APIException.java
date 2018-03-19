/*
* Copyright 2018 Builders
*************************************************************
*Nome     : APIException.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package br.com.builders.treinamento.exception;

import org.springframework.http.HttpStatus;

public abstract class APIException extends Exception {

  private static final long serialVersionUID = 1L;

  private final HttpStatus status;

  public APIException(HttpStatus status, Throwable cause) {
    super(cause);
    if (status == null) {
      throw new IllegalArgumentException();
    }
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }

}
