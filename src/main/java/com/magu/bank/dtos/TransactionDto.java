package com.magu.bank.dtos;

import java.time.LocalDateTime;

public record TransactionDto(String type, String originNum, String destinyNum) {
}
