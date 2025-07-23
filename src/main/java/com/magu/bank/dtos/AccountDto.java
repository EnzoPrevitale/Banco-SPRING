package com.magu.bank.dtos;
import com.magu.bank.models.User;

public record AccountDto(String agency, String accountNumber, Double balance, String type, User client) {
}
