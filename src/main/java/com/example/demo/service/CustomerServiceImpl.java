package com.example.demo.service;

import com.example.demo.dao.CustomerDao;
import com.example.demo.model.Account;
import com.example.demo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private AccountService accountService;

    @Override
    public List<Customer> getAll() {
        return customerDao.findAll();
    }

    @Override
    public Optional<Customer> getById(Long id) {
        return customerDao.findById(id);
    }

    @Override
    public Customer getCustomerByAccountNumber(Integer accountNumber) {
        Optional<Account> account = accountService.getAccountByAccountNumber(accountNumber);
        return account.map(Account::getCustomer).orElseThrow(() -> new RuntimeException("No customer found with account number: " + accountNumber));
    }
}
