package com.magu.bank.repositories;

import com.magu.bank.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    boolean existsByAccountNumber(String accountNumber);
}
