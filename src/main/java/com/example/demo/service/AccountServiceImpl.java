package com.example.demo.service;

import com.example.demo.dao.AccountDao;
import com.example.demo.model.Account;
import com.example.demo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private CustomerService customerService;

    @Override
    public List<Account> getAll() {
        return accountDao.findAll();
    }

    @Override
    public Account createAccount(Integer accountNumber, Long customerId) {
        Customer customer = getCustomerById(customerId);
        Optional<Account> account = accountDao.findAccountByAccountNumber(accountNumber);
        if(account.isPresent()){
            throw new RuntimeException("Account with number: " + accountNumber + " is already present");
        } else {
            return accountDao.save(new Account.Builder().customer(customer).accountNumber(accountNumber).build());
        }
    }

    @Override
    public List<Account> getAccountsByCustomerId(Long customerId) {
        Customer customer = getCustomerById(customerId);
        return accountDao.getAccountsByCustomer(customer);
    }

    @Override
    public Optional<Account> getAccountByAccountNumber(Integer accountNumber) {
        return accountDao.findAccountByAccountNumber(accountNumber);
    }

    private Customer getCustomerById(Long id) {
        Optional<Customer> customer = customerService.getById(id);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new RuntimeException("No customer found with id : " + id);
        }
    }
}
