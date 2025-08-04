package com.magu.bank.controllers;

import com.magu.bank.dtos.TransactionDto;
import com.magu.bank.models.Transaction;
import com.magu.bank.services.TransactionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @GetMapping
    public ResponseEntity<List<Transaction>> getTransactions() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @PostMapping
    public ResponseEntity<Transaction> postTransaction(@RequestBody TransactionDto dto) {
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(dto, transaction);
        transaction = service.findAccounts(transaction, dto.originNum(), dto.destinyNum());
        transaction.setDateTime(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }
}
