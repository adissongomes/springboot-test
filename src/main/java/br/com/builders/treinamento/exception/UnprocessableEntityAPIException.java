/*
* Copyright 2018 Builders
*************************************************************
*Nome     : UnprocessableEntityAPIException.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package br.com.builders.treinamento.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

public class UnprocessableEntityAPIException extends ErrorMessageAPIException {

  private static final long serialVersionUID = 1L;

  public UnprocessableEntityAPIException(String errorCode) {
    super(HttpStatus.UNPROCESSABLE_ENTITY, errorCode);
  }

  public UnprocessableEntityAPIException(List<ErrorMessage> errorCodes) {
    super(HttpStatus.UNPROCESSABLE_ENTITY, errorCodes);
  }

}
