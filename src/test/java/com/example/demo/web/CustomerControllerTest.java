package com.example.demo.web;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {
    private final Integer ACCOUNT_NUMBER = 111;
    private final String CUSTOMER_FORENAME_1 = "Test1";
    private final String CUSTOMER_FORENAME_2 = "Test1";

    @Mock
    private CustomerService customerService;
    @InjectMocks
    private CustomerController customerController;

    @Test
    public void getAll_shouldReturnAllCustomers() {
        given(customerService.getAll()).willReturn(Arrays.asList(
                new Customer.Builder().forename(CUSTOMER_FORENAME_1).build(),
                new Customer.Builder().forename(CUSTOMER_FORENAME_2).build()));

        List<Customer> customers = customerController.getAllCustomers();

        verify(customerService).getAll();
        assertThat(customers.size()).isEqualTo(2);
        assertThat(customers.get(0).getForename()).isEqualTo(CUSTOMER_FORENAME_1);
        assertThat(customers.get(1).getForename()).isEqualTo(CUSTOMER_FORENAME_2);
    }

    @Test
    public void getCustomerByAccountNumber_shouldReturnCustomerByAccountNumber() {
        given(customerService.getCustomerByAccountNumber(ACCOUNT_NUMBER)).willReturn(new Customer.Builder().forename(CUSTOMER_FORENAME_1).build());

        Customer customer = customerController.getCustomerByAccountNumber(ACCOUNT_NUMBER);

        verify(customerService).getCustomerByAccountNumber(ACCOUNT_NUMBER);
        assertThat(customer.getForename()).isEqualTo(CUSTOMER_FORENAME_1);
    }
}
