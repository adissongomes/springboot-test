/*
* Copyright 2018 Builders
*************************************************************
*Nome     : SampleResource.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package br.com.builders.treinamento.resources;

import java.util.ArrayList;
import java.util.List;

import javax.naming.ServiceUnavailableException;
import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.builders.treinamento.dto.response.SampleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@Api(value = "API Sample")
public class SampleResource {

  
  @ApiOperation(value = "Listar Samples", httpMethod = "GET", response = SampleResponse.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Lista de Sample"),
      @ApiResponse(code = 500, message = "Erro interno do sistema"),
      @ApiResponse(code = 503, message = "Erro ao acessar um servico/servidor"),
      @ApiResponse(code = 400, message = "Erro nos parametros no request"),
      @ApiResponse(code = 422, message = "Erro de negocio")})
  @RequestMapping(value = "/samples", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
  @ResponseStatus(value = HttpStatus.OK)
  public List<SampleResponse> listarSamples(
      @ApiParam(name = "apikey", required = true, value = "apikey") @RequestHeader(required = true) final String apikey)
      throws ServiceUnavailableException {
    
    List<SampleResponse> response = new ArrayList<>();
    response.add(SampleResponse.builder().id("sample-id").build());

    return response;
  }



}
