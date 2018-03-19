/*
* Copyright 2018 Builders
*************************************************************
*Nome     : SampleRequest.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package br.com.builders.treinamento.dto.request;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import br.com.builders.treinamento.exception.ErrorCodes;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@ApiModel(value = "SampleRequest")
public class SampleRequest {

    @NotNull(message = ErrorCodes.REQUIRED_FIELD_NOT_INFORMED)
    @Id
    private String id;


}
