/*
* Copyright 2018 Builders
*************************************************************
*Nome     : CustomerResource.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package br.com.builders.treinamento.resources;

import br.com.builders.treinamento.dto.request.CustomerPartialRequest;
import br.com.builders.treinamento.dto.request.CustomerRequest;
import br.com.builders.treinamento.dto.response.CustomerResponse;
import br.com.builders.treinamento.exception.NotFoundException;
import br.com.builders.treinamento.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@Api(value = "Operations available to customers / container owners")
public class CustomerResource {

  public static final String CUSTOMERS_URI = "/api/customers";

  @Autowired
  private CustomerService customerService;

  @ApiOperation(value = "List all customers / containers",
          httpMethod = "GET", response = CustomerResponse.class, tags = "customers")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Lista de clientes"),
          @ApiResponse(code = 500, message = "Erro interno do sistema"),
          @ApiResponse(code = 400, message = "Erro nos parametros no request"),
          @ApiResponse(code = 422, message = "Erro de negocio")})
  @RequestMapping(value = CUSTOMERS_URI, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
  public ResponseEntity<List<CustomerResponse>> fetchCustomers() {
    return ResponseEntity.ok(customerService.fetchAll());
  }

  @ApiOperation(value = "Creates new customer", httpMethod = "POST", tags = "system")
  @ApiResponses(value = {
          @ApiResponse(code = 201, message = "Salva novo cliente"),
          @ApiResponse(code = 500, message = "Erro interno do sistema"),
          @ApiResponse(code = 400, message = "Erro nos parametros no request"),
          @ApiResponse(code = 409, message = "Login já cadastrado")
  })
  @RequestMapping(value = CUSTOMERS_URI, method = RequestMethod.POST,
          consumes = MediaType.APPLICATION_JSON,
          produces = MediaType.APPLICATION_JSON)
  public ResponseEntity create(@Validated @RequestBody CustomerRequest customer) {
    CustomerResponse saved = customerService.save(customer);

    UriComponents resourceUri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{customerId}")
            .buildAndExpand(saved.getId());

    return ResponseEntity.created(resourceUri.toUri()).build();
  }

  @ApiOperation(value = "List all data about a customer",
          httpMethod = "GET", response = CustomerResponse.class, tags = "customers")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Recupera um cliente"),
          @ApiResponse(code = 404, message = "Cliente não encontrado")})
  @RequestMapping(value = CUSTOMERS_URI + "/{customerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
  public ResponseEntity<CustomerResponse> fetchCustomer(@PathVariable("customerId") String id) throws NotFoundException {
    return ResponseEntity.ok(customerService.fetch(id));
  }

  @ApiOperation(value = "Replaces a costumer", httpMethod = "PUT", tags = "system")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Dados do cliente substituidos"),
          @ApiResponse(code = 500, message = "Erro interno do sistema"),
          @ApiResponse(code = 400, message = "Erro nos parametros no request"),
          @ApiResponse(code = 404, message = "Cliente não encontrado")
  })
  @RequestMapping(value = CUSTOMERS_URI + "/{customerId}", method = RequestMethod.PUT,
          consumes = MediaType.APPLICATION_JSON)
  public ResponseEntity replace(@Validated @RequestBody CustomerRequest customer, @PathVariable("customerId") String id) throws NotFoundException {
    customerService.update(customer, id);
    return ResponseEntity.ok().build();
  }

  @ApiOperation(value = "Modifies a customer", httpMethod = "PATCH", tags = "system")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Dados do cliente atualizados"),
          @ApiResponse(code = 500, message = "Erro interno do sistema"),
          @ApiResponse(code = 400, message = "Erro nos parametros no request"),
          @ApiResponse(code = 404, message = "Cliente não encontrado")
  })
  @RequestMapping(value = CUSTOMERS_URI + "/{customerId}", method = RequestMethod.PATCH,
          consumes = MediaType.APPLICATION_JSON)
  public ResponseEntity partialUpdate(@Validated @RequestBody CustomerPartialRequest customer, @PathVariable("customerId") String id) throws NotFoundException {
    customerService.partialUpdate(customer, id);
    return ResponseEntity.ok().build();
  }

  @ApiOperation(value = "Deletes a customer", httpMethod = "DELETE", tags = "system")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Cliente removido com sucesso"),
          @ApiResponse(code = 404, message = "Cliente não encontrado")
  })
  @RequestMapping(value = CUSTOMERS_URI + "/{customerId}", method = RequestMethod.DELETE)
  public ResponseEntity delete(@PathVariable("customerId") String id) throws NotFoundException {
    customerService.delete(id);
    return ResponseEntity.ok().build();
  }

}
