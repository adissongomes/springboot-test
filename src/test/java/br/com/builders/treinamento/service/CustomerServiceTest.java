package br.com.builders.treinamento.service;

import br.com.builders.treinamento.domain.Customer;
import br.com.builders.treinamento.dto.response.CustomerResponse;
import br.com.builders.treinamento.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerService service;

    @Test
    public void fetchAll() {

        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(CustomersTestFactory.defaultCustomer()));

        List<CustomerResponse> customers = service.fetchAll();

        assertFalse("Customers could not be empty", customers.isEmpty());
        assertEquals(customers.get(0).getName(), "adissongomes_Test");

    }

    @Test
    public void fetchAllWhitEmptyResult() {

        Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());

        List<CustomerResponse> customers = service.fetchAll();

        assertTrue("Customers should be empty", customers.isEmpty());

    }

}