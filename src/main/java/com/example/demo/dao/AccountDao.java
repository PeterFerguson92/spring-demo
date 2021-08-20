package com.example.demo.dao;

import com.example.demo.model.Account;
import com.example.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountDao extends JpaRepository<Account, Long> {
    @Override
    List<Account> findAll();
    Optional<Account> findAccountByAccountNumber(Integer accountNumber);
    List<Account> getAccountsByCustomer(Customer customer);

}