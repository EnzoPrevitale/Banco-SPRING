package com.magu.bank.services;

import com.magu.bank.models.Account;
import com.magu.bank.models.User;
import com.magu.bank.repositories.AccountRepository;
import com.magu.bank.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountService {

    private Random random = new Random();

    @Autowired
    public AccountRepository repository;
    @Autowired
    private UserRepository userRepository;

    // Obter todos
    public List<Account> getAccounts() {
        return repository.findAll();
    }

    // Obter por id
    public Optional<Account> getAccount(Integer id) {
        return repository.findById(id);
    }

    // Apagar usuário
    public Account deleteAccount(Integer id) {
        Optional<Account> account = getAccount(id);
        if(account.isPresent()) {
            Account accountModel = account.get();
            repository.delete(accountModel);
            return accountModel;
        }else {
            throw new RuntimeException("Account not found");
        }
    }

    // Gerar número de conta
    public Account generateAccountNumber(Account account, String agency) {
        String accountNumber;
        do {
            int number = random.nextInt(99999999);
            accountNumber = String.valueOf(agency) + "/" + number;
        } while(repository.existsByAccountNumber(accountNumber));

        account.setAccountNumber(accountNumber);
        repository.save(account);
        
        return account;
    }


    // Salvar conta com id de cliente
    public Account findClient(Account account, Integer id) {
        User client = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        account.setClient(client);
        repository.save(account);
        return account;
    }

}
