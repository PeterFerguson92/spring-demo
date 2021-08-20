package com.example.demo.service;

import com.example.demo.dao.CustomerDao;
import com.example.demo.model.Account;
import com.example.demo.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
    private final Integer ACCOUNT_NUMBER = 111;
    private final String CUSTOMER_FORENAME_1 = "Test1";
    private final String CUSTOMER_FORENAME_2 = "Test2";

    @Mock
    private CustomerDao customerDao;
    @Mock
    private AccountService accountService;
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    public void getAll_shouldReturnAllCustomers() {
        given(customerDao.findAll()).willReturn(Arrays.asList(
                new Customer.Builder().forename(CUSTOMER_FORENAME_1).build(),
                new Customer.Builder().forename(CUSTOMER_FORENAME_2).build())
        );
        List<Customer> customers = customerService.getAll();

        verify(customerDao).findAll();
        assertThat(customers.size()).isEqualTo(2);
        assertThat(customers.get(0).getForename()).isEqualTo(CUSTOMER_FORENAME_1);
        assertThat(customers.get(1).getForename()).isEqualTo(CUSTOMER_FORENAME_2);
    }

    @Test
    public void getCustomerByAccountNumber_shouldReturnCustomerByAccountNumber() {
        Customer c1 = new Customer.Builder().forename(CUSTOMER_FORENAME_1).build();
        given(accountService.getAccountByAccountNumber(ACCOUNT_NUMBER)).willReturn(Optional.of(new Account.Builder().accountNumber(ACCOUNT_NUMBER).customer(c1).build()));

        Customer customer = customerService.getCustomerByAccountNumber(ACCOUNT_NUMBER);

        verify(accountService).getAccountByAccountNumber(ACCOUNT_NUMBER);
        assertThat(customer.getForename()).isEqualTo(CUSTOMER_FORENAME_1);
    }
}
