package com.example.demo.web;

import com.example.demo.model.Account;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Account> getAllAccounts() {
        return accountService.getAll();
    }

    @RequestMapping(path = "/getAccountsByCustomerId", method = RequestMethod.GET)
    public List<Account> getAccountsByCustomerId(@RequestParam Long customerId) {
        return accountService.getAccountsByCustomerId(customerId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Account createAccount(@RequestParam Integer accountNumber, @RequestParam Long customerId) {
        return accountService.createAccount(accountNumber, customerId);
    }
}
