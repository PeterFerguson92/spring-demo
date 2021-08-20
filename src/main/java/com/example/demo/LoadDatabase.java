package com.example.demo;

import com.example.demo.dao.AccountDao;
import com.example.demo.dao.CustomerDao;
import com.example.demo.model.Account;
import com.example.demo.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Bean
    CommandLineRunner initDatabase(CustomerDao customerDao, AccountDao accountDao) {

        return args -> {
            Customer c1 = new Customer.Builder().forename("John").surname("Doe").dateOfBirth(LocalDate.now()).build();
            Customer c2 = new Customer.Builder().forename("Micheal").surname("Francis").dateOfBirth(LocalDate.now().minusDays(1)).build();
            Account a1 = new Account.Builder().customer(c1).accountNumber(11111).build();
            log.info("Preloading customer  " + c1.getForename() + " - " + c1.getSurname(), customerDao.save(c1));
            log.info("Preloading customer  " + c2.getForename() + " - " + c2.getSurname(), customerDao.save(c2));
            log.info("Preloading account  " + a1.getAccountNumber(), accountDao.save(a1));
        };
    }
}
