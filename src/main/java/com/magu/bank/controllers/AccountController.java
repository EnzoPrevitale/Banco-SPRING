package com.magu.bank.controllers;

import com.magu.bank.dtos.AccountDto;
import com.magu.bank.models.Account;
import com.magu.bank.services.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    Random random = new Random();

    @Autowired
    AccountService service;

    // Obter todos
    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAccounts());
    }

    // Salvar novo
    @PostMapping
    public ResponseEntity<Account> postAccount(@RequestBody AccountDto dto) {
        Account account = new Account();
        BeanUtils.copyProperties(dto, account);
        account.setBalance(0.0);
        account = service.findClient(account, dto.clientId());
        account.setAccountNumber(service.generateAccountNumber(dto.agency()));
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    // Apagar
    @DeleteMapping("/{id}")
    public ResponseEntity<Account> deleteAccount(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteAccount(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity putAccount(@PathVariable Integer id, @RequestBody AccountDto dto) {
        Optional<Account> account = service.getAccount(id);
        if(account.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
        BeanUtils.copyProperties(dto, account.get(), "id", "accountNumber", "client");
        Account acc = service.repository.save(service.findClient(account.get(), dto.clientId()));
        return ResponseEntity.status(HttpStatus.OK).body(acc);
    }
}
