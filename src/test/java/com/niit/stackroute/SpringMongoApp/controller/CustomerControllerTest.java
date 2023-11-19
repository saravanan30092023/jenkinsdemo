package com.niit.stackroute.SpringMongoApp.controller;

import com.niit.stackroute.SpringMongoApp.model.*;

import com.niit.stackroute.SpringMongoApp.exception.*;
import com.niit.stackroute.SpringMongoApp.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerServiceImpl customerService;

    @InjectMocks
    private CustomerController customerController;

    private Customer customer1, customer2;
    private Address address1 , address2;
    List<Customer> customerList;

    @BeforeEach
    void setUp() {
        address1 = new Address("city1","state1","pincode1");
        customer1 = new Customer(1001,"Johny",address1,1234);
        address2 = new Address("city2","state2","pincode2");
        customer2 = new Customer(1002,"Harry",address2,87654);
        customerList = Arrays.asList(customer1,customer2);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

    }

    @AfterEach
    void tearDown() {
        customer1=null;
        customer2 = null;
    }
    @Test
    public void givenCustomerToSaveReturnSavedCustomer() throws Exception {

        when(customerService.saveCustomerDetail(any())).thenReturn(customer1); // stubbing

        //chaining
        mockMvc.perform(post("/api/v1/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(customer1)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());

        verify(customerService,times(1)).saveCustomerDetail(any());

    }

    @Test
    public void givenCustomerToSaveReturnSavedCustomerFailure() throws Exception {
        when(customerService.saveCustomerDetail(any())).thenThrow(CustomerAlreadyExistsException.class);

        mockMvc.perform(post("/api/v1/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(customer1)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
        verify(customerService,times(1)).saveCustomerDetail(any());

    }

    @Test
    public void givenCustomerIdDeleteCustomer() throws Exception {
        when(customerService.deleteCustomer(anyInt())).thenReturn(true);
        mockMvc.perform(delete("/api/v1/customer/1001")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(customerService,times(1)).deleteCustomer(anyInt());

    }


    private static String jsonToString(final Object ob) throws JsonProcessingException {
        String result;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(ob);
            result = jsonContent;
        } catch(JsonProcessingException e) {
            result = "JSON processing error";
        }

        return result;
    }

}
