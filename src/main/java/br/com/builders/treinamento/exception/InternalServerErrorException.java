/*
* Copyright 2018 Builders
*************************************************************
*Nome     : InternalServerErrorException.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package br.com.builders.treinamento.exception;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends APIException {

  private static final long serialVersionUID = 1L;

  public InternalServerErrorException(final Throwable cause) {
    super(HttpStatus.INTERNAL_SERVER_ERROR, cause);
  }
}
