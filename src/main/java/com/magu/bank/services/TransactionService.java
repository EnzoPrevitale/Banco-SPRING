package com.magu.bank.services;

import com.magu.bank.dtos.TransactionDto;
import com.magu.bank.models.Account;
import com.magu.bank.models.Transaction;
import com.magu.bank.repositories.AccountRepository;
import com.magu.bank.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;
    private AccountRepository accountRepository;

    public List<Transaction> getAll() {
        return repository.findAll();
    }

    // Salvar transação com id de contas de origem e destino
    public Transaction findAccounts(Transaction transaction, String originNum, String destinyNum) {
        Account origin = accountRepository.findByAccountNumber(originNum).orElseThrow(() -> new RuntimeException("Not Found"));
        Account destiny = accountRepository.findByAccountNumber(originNum).orElseThrow(() -> new RuntimeException("Not Found"));
        transaction.setOrigin(origin);
        transaction.setDestiny(destiny);
        repository.save(transaction);
        return transaction;
    }

}
