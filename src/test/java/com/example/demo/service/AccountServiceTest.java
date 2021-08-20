package com.example.demo.service;

import com.example.demo.dao.AccountDao;
import com.example.demo.model.Account;
import com.example.demo.model.Customer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
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
public class AccountServiceTest {
    final Long CUSTOMER_ID = 123L;
    private final Integer ACCOUNT_NUMBER_1 = 111;
    private final Integer ACCOUNT_NUMBER_2 = 111;
    private final String CUSTOMER_FORENAME_1 = "Test1";

    @Mock
    private AccountDao accountDao;
    @Mock
    private CustomerService customerService;
    @Captor
    private ArgumentCaptor<Account> accountArgumentCaptor;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @InjectMocks
    private AccountServiceImpl accountService;


    @Test
    public void getAll_shouldReturnAllAccounts() {
        given(accountDao.findAll()).willReturn(Arrays.asList(
                new Account.Builder().accountNumber(ACCOUNT_NUMBER_1).build(),
                new Account.Builder().accountNumber(ACCOUNT_NUMBER_2).build())
        );
        List<Account> accounts = accountService.getAll();

        verify(accountDao).findAll();
        assertThat(accounts.size()).isEqualTo(2);
        assertThat(accounts.get(0).getAccountNumber()).isEqualTo(ACCOUNT_NUMBER_1);
        assertThat(accounts.get(1).getAccountNumber()).isEqualTo(ACCOUNT_NUMBER_2);
    }

    @Test
    public void getAccountsByCustomerId_shouldReturnAccountsByCustomerId() {
        Customer c1 = new Customer.Builder().id(CUSTOMER_ID).forename(CUSTOMER_FORENAME_1).build();
        given(customerService.getById(CUSTOMER_ID)).willReturn(Optional.of(c1));
        given(accountDao.getAccountsByCustomer(c1)).willReturn(Arrays.asList(
                new Account.Builder().accountNumber(ACCOUNT_NUMBER_1).customer(c1).build())
        );

        List<Account> accounts = accountService.getAccountsByCustomerId(CUSTOMER_ID);

        verify(accountDao).getAccountsByCustomer(c1);
        assertThat(accounts.size()).isEqualTo(1);
        assertThat(accounts.get(0).getAccountNumber()).isEqualTo(ACCOUNT_NUMBER_1);
    }

    @Test
    public void createAccount_shouldCreateAccountForCustomer() {
        Customer c1 = new Customer.Builder().id(CUSTOMER_ID).forename(CUSTOMER_FORENAME_1).build();
        given(customerService.getById(CUSTOMER_ID)).willReturn(Optional.of(c1));
        given(accountDao.findAccountByAccountNumber(ACCOUNT_NUMBER_1)).willReturn(Optional.empty());

        accountService.createAccount(ACCOUNT_NUMBER_1,CUSTOMER_ID);

        verify(accountDao).save(accountArgumentCaptor.capture());
        Account savedAccount = accountArgumentCaptor.getValue();
        assertThat(savedAccount.getAccountNumber()).isEqualTo(ACCOUNT_NUMBER_1);
        assertThat(savedAccount.getCustomer().getForename()).isEqualTo(CUSTOMER_FORENAME_1);
    }

    @Test
    public void createAccount_shouldThrowExceptionWhenCustomerIsNotFound() {
        given(customerService.getById(CUSTOMER_ID)).willReturn(Optional.empty());

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("No customer found with id : 123");

        accountService.createAccount(ACCOUNT_NUMBER_1,CUSTOMER_ID);
    }

    @Test
    public void createAccount_shouldThrowExceptionWhenAccountNumberIsFound() {
        Customer c1 = new Customer.Builder().id(CUSTOMER_ID).forename(CUSTOMER_FORENAME_1).build();
        given(customerService.getById(CUSTOMER_ID)).willReturn(Optional.of(c1));
        given(accountDao.findAccountByAccountNumber(ACCOUNT_NUMBER_1)).willReturn(Optional.of(new Account.Builder().accountNumber(ACCOUNT_NUMBER_1).build()));

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Account with number: 111 is already present");

        accountService.createAccount(ACCOUNT_NUMBER_1,CUSTOMER_ID);
    }
}
