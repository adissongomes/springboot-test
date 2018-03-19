/*
* Copyright 2018 Builders
*************************************************************
*Nome     : ServiceUnavailableException.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package br.com.builders.treinamento.exception;

import org.springframework.http.HttpStatus;

public class ServiceUnavailableException extends APIException {

  private static final long serialVersionUID = 1L;

  public ServiceUnavailableException(final Throwable cause) {
    super(HttpStatus.SERVICE_UNAVAILABLE, cause);
  }
}

