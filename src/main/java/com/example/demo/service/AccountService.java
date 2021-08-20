package com.example.demo.service;


import com.example.demo.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<Account> getAll();
    Account createAccount(Integer accountNumber, Long customerId);
    Optional<Account> getAccountByAccountNumber(Integer accountNumber);
    List<Account> getAccountsByCustomerId(Long customerId);
}
