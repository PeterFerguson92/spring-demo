package com.example.demo.web;

import com.example.demo.model.Account;
import com.example.demo.service.AccountService;
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
public class AccountControllerTest {
    final Long CUSTOMER_ID = 123L;
    final Integer ACCOUNT_NUMBER_1 = 111;
    final Integer ACCOUNT_NUMBER_2 = 222;

    @Mock
    private AccountService accountService;
    @InjectMocks
    private AccountController accountController;

    @Test
    public void getAll_shouldReturnAllAccounts() {
        given(accountService.getAll()).willReturn(Arrays.asList(
                new Account.Builder().accountNumber(ACCOUNT_NUMBER_1).build(),
                new Account.Builder().accountNumber(ACCOUNT_NUMBER_2).build())
        );

        List<Account> accounts = accountController.getAllAccounts();

        verify(accountService).getAll();
        assertThat(accounts.size()).isEqualTo(2);
        assertThat(accounts.get(0).getAccountNumber()).isEqualTo(ACCOUNT_NUMBER_1);
        assertThat(accounts.get(1).getAccountNumber()).isEqualTo(ACCOUNT_NUMBER_2);
    }

    @Test
    public void getAccountsByCustomerId_shouldReturnAllAccountsForCustomer() {
        given(accountService.getAccountsByCustomerId(CUSTOMER_ID)).willReturn(Arrays.asList(
                new Account.Builder().accountNumber(ACCOUNT_NUMBER_1).build(),
                new Account.Builder().accountNumber(ACCOUNT_NUMBER_2).build()));

        List<Account> accounts = accountController.getAccountsByCustomerId(CUSTOMER_ID);

        verify(accountService).getAccountsByCustomerId(CUSTOMER_ID);
        assertThat(accounts.size()).isEqualTo(2);
        assertThat(accounts.get(0).getAccountNumber()).isEqualTo(111);
        assertThat(accounts.get(1).getAccountNumber()).isEqualTo(222);
    }

    @Test
    public void createAccount_shouldCreateNewAccountForCustomer() {
        given(accountService.createAccount(ACCOUNT_NUMBER_1, CUSTOMER_ID)).willReturn(new Account.Builder().accountNumber(ACCOUNT_NUMBER_1).build());

        Account account = accountController.createAccount(ACCOUNT_NUMBER_1, CUSTOMER_ID);

        verify(accountService).createAccount(ACCOUNT_NUMBER_1, CUSTOMER_ID);
        assertThat(account.getAccountNumber()).isEqualTo(ACCOUNT_NUMBER_1);
    }
}
