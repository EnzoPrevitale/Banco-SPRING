package com.magu.bank.services;

import com.magu.bank.models.Account;
import com.magu.bank.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    // Obter todos
    public List<Account> getAccounts() {
        return repository.findAll();
    }

    // Obter por id
    public Optional<Account> getAccount(Integer id) {
        return repository.findById(id);
    }

}
