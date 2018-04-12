/*
* Copyright 2018 Builders
*************************************************************
*Nome     : BuildersService.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package br.com.builders.treinamento.service;

import br.com.builders.treinamento.domain.Customer;
import br.com.builders.treinamento.dto.request.CustomerPartialRequest;
import br.com.builders.treinamento.dto.request.CustomerRequest;
import br.com.builders.treinamento.dto.response.CustomerResponse;
import br.com.builders.treinamento.exception.NotFoundException;
import br.com.builders.treinamento.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<CustomerResponse> fetchAll() {
        return repository.findAll().stream().map(c -> buildResponse(c)).collect(Collectors.toList());
    }

    public CustomerResponse save(CustomerRequest customerRequest) {
        Customer savedCustomer = repository.save(buildFromRequest(customerRequest));
        return buildResponse(savedCustomer);
    }

    public CustomerResponse update(CustomerRequest customerRequest, String id) throws NotFoundException {
        CustomerResponse customerResponse = fetch(id);

        Customer customer = buildFromRequest(customerRequest);
        customer.setId(customerResponse.getId());

        return buildResponse(repository.save(customer));
    }

    public CustomerResponse partialUpdate(CustomerPartialRequest customerRequest, String id) throws NotFoundException {
        CustomerResponse customerResponse = fetch(id);

        Customer.CustomerBuilder builder = Customer.builder()
                .id(customerResponse.getId());

        if (!StringUtils.isEmpty(customerRequest.getLogin()))
            builder.login(customerRequest.getLogin());
        else
            builder.login(customerResponse.getLogin());

        if (!StringUtils.isEmpty(customerRequest.getName()))
            builder.name(customerRequest.getName());
        else
            builder.name(customerResponse.getName());

        if (!StringUtils.isEmpty(customerRequest.getBaseUrl()))
            builder.baseUrl(customerRequest.getBaseUrl());
        else
            builder.baseUrl(customerResponse.getBaseUrl());

        if (!StringUtils.isEmpty(customerRequest.getCrmId()))
            builder.crmId(customerRequest.getCrmId());
        else
            builder.crmId(customerResponse.getCrmId());

        Customer customerToUpdate = builder.build();
        repository.save(customerToUpdate);

        return buildResponse(customerToUpdate);
    }

    public CustomerResponse fetch(String id) throws NotFoundException {
        Customer customer = repository.findOne(id);
        if (customer == null) {
            throw new NotFoundException("Customer not found");
        }
        return buildResponse(customer);
    }

    private Customer buildFromRequest(CustomerRequest customerRequest) {
        return Customer.builder()
                .name(customerRequest.getName())
                .crmId(customerRequest.getCrmId())
                .login(customerRequest.getLogin())
                .baseUrl(customerRequest.getBaseUrl())
                .build();
    }
    private CustomerResponse buildResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .crmId(customer.getCrmId())
                .login(customer.getLogin())
                .baseUrl(customer.getBaseUrl())
                .build();
    }

    public void delete(String id) throws NotFoundException {
        fetch(id);
        repository.delete(id);
    }
}
