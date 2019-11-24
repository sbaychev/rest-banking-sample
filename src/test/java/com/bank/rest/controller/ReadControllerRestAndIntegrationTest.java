package com.bank.rest.controller;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bank.rest.data.Customer;
import com.bank.rest.services.IAccountReadService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ReadControllerRestAndIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IAccountReadService iAccountReadServices;

    private static final String DEFAULT_CUSTOMER_USERNAME = "jon01";

    @Test
    public void givenExistingCustomer_whenCustomerRequested_thenResourceRetrieved() throws Exception {

        given(this.iAccountReadServices.getAccountHolder(DEFAULT_CUSTOMER_USERNAME))
            .willReturn(Customer.builder()
                .accounts(null)
                .firstName("John")
                .lastName("Smith")
                .username("jon01").build());

        this.mvc.perform(
            get("/api/customer")
                .param("username", DEFAULT_CUSTOMER_USERNAME))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username", is(DEFAULT_CUSTOMER_USERNAME)));

        verify(iAccountReadServices, VerificationModeFactory.times(1)).getAccountHolder(Mockito.any());
        reset(iAccountReadServices);
    }

//TODO: Move to Write ControllerIntegration Test
//    @Test
//    public void whenValidCreditInput_thenCreateTransaction() throws IOException, Exception {
//        Customer jon = new Customer("jon");
//        mvc.perform(post("/api/customer").contentType(MediaType.APPLICATION_JSON).content(JtoJson(jon)));
//
//        List<Customer> found = repository.findAll();
//        assertThat(found).extracting(Customer::getName).containsOnly("jon");
//    }
}
