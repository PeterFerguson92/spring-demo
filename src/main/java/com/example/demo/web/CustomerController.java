package com.example.demo.web;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> getAllCustomers() {
        return customerService.getAll();
    }

    @RequestMapping(path = "/getCustomerByAccountNumber", method = RequestMethod.GET)
    public Customer getCustomerByAccountNumber(@RequestParam Integer accountNumber) {
        return customerService.getCustomerByAccountNumber(accountNumber);
    }
}
