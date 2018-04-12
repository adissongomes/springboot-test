package br.com.builders.treinamento.resources;

import br.com.builders.treinamento.dto.request.CustomerPartialRequest;
import br.com.builders.treinamento.dto.request.CustomerRequest;
import br.com.builders.treinamento.exception.GlobalExceptionHandler;
import br.com.builders.treinamento.exception.NotFoundException;
import br.com.builders.treinamento.service.CustomerService;
import br.com.builders.treinamento.service.CustomersTestFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerResourceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerResourceTest.class);

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerResource customerResource;

    private MockMvc mvc;

    private ObjectMapper mapper;

    @Before
    public void init() {
        mvc = MockMvcBuilders.standaloneSetup(customerResource)
                .setControllerAdvice(new GlobalExceptionHandler(Mockito.mock(MessageSource.class)))
                .build();
        mapper = new ObjectMapper();
    }

    /* FETCH ALL */

    @Test
    public void testFetchAll() throws Exception {
        when(customerService.fetchAll()).thenReturn(Arrays.asList(CustomersTestFactory.defaultCustomerResponse()));

        mvc.perform(MockMvcRequestBuilders
                .get(CustomerResource.CUSTOMERS_URI))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", equalTo("adissongomes_Test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].login", equalTo("test@test.com")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testFetchAllWhenResultIsEmpty() throws Exception {
        when(customerService.fetchAll()).thenReturn(new ArrayList<>());

        mvc.perform(MockMvcRequestBuilders
                .get(CustomerResource.CUSTOMERS_URI))
                .andExpect(MockMvcResultMatchers.jsonPath("$", empty()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /* SAVE */

    @Test
    public void testSave() throws Exception {
        when(customerService.save(CustomersTestFactory.defaultCustomerRequest()))
                .thenReturn(CustomersTestFactory.defaultCustomerResponse());

        mvc.perform(MockMvcRequestBuilders
                .post(CustomerResource.CUSTOMERS_URI)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(CustomersTestFactory.defaultCustomerResponse()))
                )
                .andDo(res -> {
                    String location = res.getResponse().getHeader("location");
                    LOGGER.info("Location: {}", location);
                    Assert.assertNotNull(location);
                });

    }

    @Test
    public void testSaveWithSomeError() throws Exception {
        when(customerService.save(CustomersTestFactory.defaultCustomerRequest()))
                .thenThrow(new RuntimeException("Any error"));

        mvc.perform(MockMvcRequestBuilders
                .post(CustomerResource.CUSTOMERS_URI)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(CustomersTestFactory.defaultCustomerResponse()))
                ).andExpect(MockMvcResultMatchers.status().isInternalServerError());

    }

    @Test
    public void testSaveWithEmptyLoginField() throws Exception {
        CustomerRequest request = CustomersTestFactory.defaultCustomerRequest();
        request.setLogin("");

        mvc.perform(MockMvcRequestBuilders
                .post(CustomerResource.CUSTOMERS_URI)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(request))
                ).andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void testSaveDuplicatedLogin() throws Exception {
        when(customerService.save(CustomersTestFactory.defaultCustomerRequest()))
                .thenThrow(new DataIntegrityViolationException("login already exists"));

        mvc.perform(MockMvcRequestBuilders
                .post(CustomerResource.CUSTOMERS_URI)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(CustomersTestFactory.defaultCustomerResponse()))
                ).andExpect(MockMvcResultMatchers.status().isConflict());

    }

    /* FETCH BY ID */

    @Test
    public void testFetch() throws Exception {
        String id = "111";
        when(customerService.fetch(id)).thenReturn(CustomersTestFactory.defaultCustomerResponse());

        mvc.perform(MockMvcRequestBuilders
                .get(CustomerResource.CUSTOMERS_URI + "/{customerId}", id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", equalTo(id)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.login", equalTo("test@test.com")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testFetchWhenNotFoundCustomer() throws Exception {
        String id = "111";
        when(customerService.fetch(id)).thenThrow(new NotFoundException("not found"));

        mvc.perform(MockMvcRequestBuilders
                .get(CustomerResource.CUSTOMERS_URI + "/{customerId}", id))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /* UPDATE */

    @Test
    public void testUpdate() throws Exception {
        String id = "111";

        CustomerRequest request = CustomersTestFactory.defaultCustomerRequest();

        mvc.perform(MockMvcRequestBuilders.put(CustomerResource.CUSTOMERS_URI + "/{customerId}", id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(request))
                )
                .andDo(result -> LOGGER.info(result.getResponse().getContentAsString()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateWithInvalidEmptyLoginField() throws Exception {
        String id = "111";
        CustomerRequest request = CustomersTestFactory.defaultCustomerRequest();
        request.setLogin("");

        mvc.perform(MockMvcRequestBuilders.put(CustomerResource.CUSTOMERS_URI + "/{customerId}", id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(request))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testUpdateWhenNotFoundCostumer() throws Exception {
        String id = "111";
        doThrow(new NotFoundException("not found"))
                .when(customerService).update(Matchers.any(CustomerRequest.class), Matchers.eq(id));

        mvc.perform(MockMvcRequestBuilders
                .put(CustomerResource.CUSTOMERS_URI + "/{customerId}", id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(CustomersTestFactory.defaultCustomerRequest()))
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /* PATCH */

    @Test
    public void testPartialModification() throws Exception {
        String id = "111";
        CustomerPartialRequest request = CustomerPartialRequest.builder()
                .name("New Name")
                .build();

        mvc.perform(MockMvcRequestBuilders.patch(CustomerResource.CUSTOMERS_URI + "/{customerId}", id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(request))
                )
                .andDo(result -> LOGGER.info(result.getResponse().getContentAsString()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testPartialUpdateWithInvalidLoginField() throws Exception {
        String id = "111";
        CustomerPartialRequest request = CustomerPartialRequest.builder()
                .name("New Name")
                .login("newlogin")
                .build();

        mvc.perform(MockMvcRequestBuilders.patch(CustomerResource.CUSTOMERS_URI + "/{customerId}", id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(request))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testPartialUpdateWhenNotFoundCostumer() throws Exception {
        String id = "111";
        doThrow(new NotFoundException("not found"))
                .when(customerService).partialUpdate(Matchers.any(CustomerPartialRequest.class), Matchers.eq(id));

        mvc.perform(MockMvcRequestBuilders
                .patch(CustomerResource.CUSTOMERS_URI + "/{customerId}", id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(CustomersTestFactory.defaultCustomerRequest()))
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testDelete() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .delete(CustomerResource.CUSTOMERS_URI + "/{customerId}", "111"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void testDeleteWhenNotFoundCustomer() throws Exception {
        String id = "111";

        doThrow(new NotFoundException("not found"))
                .when(customerService).delete(id);

        mvc.perform(MockMvcRequestBuilders
                .delete(CustomerResource.CUSTOMERS_URI + "/{customerId}", id))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
}