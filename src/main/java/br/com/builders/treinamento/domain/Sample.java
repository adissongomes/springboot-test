/*
* Copyright 2018 Builders
*************************************************************
*Nome     : Cartao.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package br.com.builders.treinamento.domain;

import org.hibernate.validator.constraints.NotBlank;

import br.com.builders.treinamento.exception.ErrorCodes;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Sample {
  
  @NotBlank(message = ErrorCodes.REQUIRED_FIELD_NOT_INFORMED)
  private String idSample;
  

}
