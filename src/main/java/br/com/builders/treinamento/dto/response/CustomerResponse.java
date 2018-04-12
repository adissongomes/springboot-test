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
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"uuid"})
@ApiModel(value = "CustomerResponse")
public class CustomerResponse {

  @ApiModelProperty(
          value = "Internal Customer ID, uniquely identifying this customer in the world.",
          example = "553fa88c-4511-445c-b33a-ddff58d76886"
  )
  private String id;

  @ApiModelProperty(
          value = "Customer ID in the CRM.",
          example = "C5265"
  )
  private String crmId;

  @ApiModelProperty(
          value = "Base URL of the customer container.",
          example = "http://www.platformbuilders.com.br"
  )
  private String baseUrl;

  @ApiModelProperty(
          value = "Customer name.",
          example = "Platform Builders"
  )
  private String name;

  @ApiModelProperty(
          value = "Admin login.",
          example = "contato@platformbuilders.com.br"
  )
  private String login;
}
