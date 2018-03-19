/*
* Copyright 2018 Builders
*************************************************************
*Nome     : BadRequestAPIException.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package br.com.builders.treinamento.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

public class BadRequestAPIException extends ErrorMessageAPIException {

  private static final long serialVersionUID = 1L;

  public BadRequestAPIException(final String errorCode) {
    super(HttpStatus.BAD_REQUEST, errorCode);
  }

  public BadRequestAPIException(final ErrorMessage erros) {
    super(HttpStatus.BAD_REQUEST, erros);
  }

  public BadRequestAPIException(final List<ErrorMessage> erros) {
    super(HttpStatus.BAD_REQUEST, erros);
  }

}
