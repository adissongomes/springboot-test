/*
* Copyright 2018 Builders
*************************************************************
*Nome     : CustomerRequest.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package br.com.builders.treinamento.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"uuid"})
@ApiModel(value = "CustomerRequest")
public class CustomerResponse {
  private String id;
  private String crmId;
  private String baseUrl;
  private String name;
  private String login;
}
