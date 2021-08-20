package com.example.demo.service;

import com.example.demo.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getAll();
    Optional<Customer> getById(Long id);
    Customer getCustomerByAccountNumber(Integer accountNumber);
}
