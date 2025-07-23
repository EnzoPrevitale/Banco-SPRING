package com.magu.bank.controllers;

import com.magu.bank.models.Account;
import com.magu.bank.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/accounts")
public class AccountController {

    @Autowired
    AccountService service;

    // Obter todos
    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAccounts());
    }

}
