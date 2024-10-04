package com.salessystem.SalesSystem.services;

import com.salessystem.SalesSystem.models.CustomerModel;
import com.salessystem.SalesSystem.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class CustomerServiceTest {

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @BeforeEach
    void setUp() {

    }

    @Test
    public void testDeleteCustomer(){
        CustomerModel customer = CustomerModel.builder().numCustomer(1L).build();
        doNothing().when(customerRepository).deleteById(customer.getNumCustomer());

        customerService.deleteCustomer(customer.getNumCustomer());

        verify(customerRepository).deleteById(customer.getNumCustomer());
    }

    @Test
    public void testGetCustomerByIdWhenCustomerExists(){
        CustomerModel customer = CustomerModel.builder()
                .numCustomer(100L).name("customer")
                .build();
        when(customerRepository.findById(customer.getNumCustomer())).thenReturn(Optional.of(customer));

        CustomerModel CustomerById =customerService.getCustomerById(customer.getNumCustomer());

        Assertions.assertEquals("customer", CustomerById.getNumCustomer());
    }
}