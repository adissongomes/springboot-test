/*
* Copyright 2018 Builders
*************************************************************
*Nome     : ErrorMessageAPIException.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package br.com.builders.treinamento.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.google.common.collect.Lists;

public abstract class ErrorMessageAPIException extends APIException {

  private static final long serialVersionUID = 1L;

  private final List<ErrorMessage> erros;

  public ErrorMessageAPIException(HttpStatus status, ErrorMessage erro) {
    super(status, null);
    if (erro == null) {
      throw new IllegalArgumentException();
    }
    this.erros = Lists.newArrayList();
    erros.add(erro);
  }

  public ErrorMessageAPIException(HttpStatus status, String erro) {
    super(status, null);
    if (erro == null) {
      throw new IllegalArgumentException();
    }
    this.erros = Lists.newArrayList();
    erros.add(new ErrorMessage(erro, "", ""));
  }

  public ErrorMessageAPIException(HttpStatus status, List<ErrorMessage> erros) {
    super(status, null);
    if (erros == null || erros.size() == 0) {
      throw new IllegalArgumentException();
    }
    this.erros = erros;
  }

  public List<ErrorMessage> getErros() {
    return erros;
  }

}
