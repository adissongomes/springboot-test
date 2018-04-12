package br.com.builders.treinamento.service;

import br.com.builders.treinamento.domain.Customer;
import br.com.builders.treinamento.dto.request.CustomerRequest;
import br.com.builders.treinamento.dto.response.CustomerResponse;

import java.util.UUID;

public class CustomersTestFactory {

    public static Customer defaultCustomer() {
        return Customer.builder()
                .id("111")
                .name("adissongomes_Test")
                .baseUrl("http://www.test.com.br")
                .login("test@test.com")
                .crmId("C4354")
                .build();
    }

    public static CustomerRequest defaultCustomerRequest() {
        Customer customer = defaultCustomer();
        return CustomerRequest.builder()
                .name(customer.getName())
                .baseUrl(customer.getBaseUrl())
                .login(customer.getLogin())
                .crmId(customer.getCrmId())
                .build();
    }

    public static CustomerResponse defaultCustomerResponse() {
        Customer customer = defaultCustomer();
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .baseUrl(customer.getBaseUrl())
                .login(customer.getLogin())
                .crmId(customer.getCrmId())
                .build();
    }

}
