package br.com.builders.treinamento.service;

import br.com.builders.treinamento.domain.Customer;
import br.com.builders.treinamento.dto.request.CustomerPartialRequest;
import br.com.builders.treinamento.dto.request.CustomerRequest;
import br.com.builders.treinamento.dto.response.CustomerResponse;
import br.com.builders.treinamento.exception.NotFoundException;
import br.com.builders.treinamento.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

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

    @Test
    public void testSave() {

        Customer customer = CustomersTestFactory.defaultCustomer();

        Mockito.when(repository.save(any(Customer.class))).thenReturn(customer);

        CustomerResponse response = service.save(CustomersTestFactory.defaultCustomerRequest());

        assertEquals(customer.getId(), response.getId());
        assertEquals(customer.getLogin(), response.getLogin());

    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testSaveWhenCustomerAlreadyExists() {

        Customer customer = CustomersTestFactory.defaultCustomer();

        Mockito.when(repository.save(any(Customer.class))).thenThrow(new DataIntegrityViolationException("duplicated key"));

        CustomerResponse response = service.save(CustomersTestFactory.defaultCustomerRequest());

    }

    @Test
    public void testPartialUpdate() throws NotFoundException {

        Customer customer = CustomersTestFactory.defaultCustomer();
        Customer savedCustomer = CustomersTestFactory.defaultCustomer();
        savedCustomer.setLogin("new_login");

        Mockito.when(repository.findOne(anyString())).thenReturn(customer);
        Mockito.when(repository.save(any(Customer.class))).thenReturn(savedCustomer);

        CustomerPartialRequest partialRequest = CustomerPartialRequest.builder()
                .login("new_login")
                .build();
        CustomerResponse response = service.partialUpdate(partialRequest, customer.getId());

        assertEquals(customer.getId(), response.getId());
        assertNotEquals(customer.getLogin(), response.getLogin());

    }

    @Test(expected = NotFoundException.class)
    public void testPartialUpdateWhenNotFoundCustomer() throws NotFoundException {

        CustomerPartialRequest partialRequest = CustomerPartialRequest.builder()
                .login("new_login")
                .build();

        Mockito.when(repository.findOne(anyString())).thenReturn(null);

        service.partialUpdate(partialRequest, "1");

    }

    @Test
    public void testUpdate() throws NotFoundException {

        Customer customer = CustomersTestFactory.defaultCustomer();
        Customer savedCustomer = CustomersTestFactory.defaultCustomer();
        savedCustomer.setLogin("new_login");
        savedCustomer.setName("new_name");

        Mockito.when(repository.findOne(anyString())).thenReturn(customer);
        Mockito.when(repository.save(any(Customer.class))).thenReturn(savedCustomer);

        CustomerRequest request = CustomerRequest.builder()
                .login("new_login")
                .name("new_name")
                .build();
        CustomerResponse response = service.update(request, customer.getId());

        assertEquals(customer.getId(), response.getId());
        assertEquals(customer.getCrmId(), response.getCrmId());
        assertNotEquals(customer.getLogin(), response.getLogin());
        assertNotEquals(customer.getName(), response.getName());

    }

    @Test(expected = NotFoundException.class)
    public void testUpdateWhenNotFoundCustomer() throws NotFoundException {

        CustomerRequest request = CustomerRequest.builder()
                .login("new_login")
                .build();

        Mockito.when(repository.findOne(anyString())).thenReturn(null);

        service.update(request, "1");

    }

    @Test(expected = NotFoundException.class)
    public void testDeleteWhenNotFoundCustomer() throws NotFoundException {

        Mockito.when(repository.findOne(anyString())).thenReturn(null);

        service.delete("1");

    }

}