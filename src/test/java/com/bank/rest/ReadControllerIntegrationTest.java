package com.bank.rest;

import com.bank.rest.services.IAccountReadServices;
import com.bank.rest.web.ReadController;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ReadController.class)
public class ReadControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IAccountReadServices customerService;

//    @Test
//    public void givenExistingCustomer_whenCustomerRequested_thenResourceRetrieved() throws Exception {
//        given(this.customerService.getCustomerDetail(DEFAULT_CUSTOMER_ID))
//            .willReturn(new Customer(DEFAULT_CUSTOMER_ID, "customerJohn", "companyOne"));
//
//        this.mvc.perform(get("/customers/" + DEFAULT_CUSTOMER_ID))
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$._links").doesNotExist())
//            .andExpect(jsonPath("$.customerId", is(DEFAULT_CUSTOMER_ID)));
//    }
}
