/*
* Copyright 2018 Builders
*************************************************************
*Nome     : CustomerRequest.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package br.com.builders.treinamento.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"uuid"})
@ApiModel(value = "CustomerRequest")
public class CustomerRequest {

  @ApiModelProperty(
          value = "Customer name.",
          example = "Platform Builders"
  )
  @NotBlank
  private String name;

  @ApiModelProperty(
          value = "Admin login.",
          example = "contato@platformbuilders.com.br"
  )
  @NotBlank
  @Email
  private String login;

  @ApiModelProperty(
          value = "Customer ID in the CRM.",
          example = "C5265"
  )
  @NotBlank
  private String crmId;

  @ApiModelProperty(
          value = "Base URL of the customer container.",
          example = "http://www.platformbuilders.com.br"
  )
  @URL
  private String baseUrl;
}
