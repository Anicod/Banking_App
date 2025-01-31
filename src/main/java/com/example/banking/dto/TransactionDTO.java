package com.example.banking.dto;

import java.time.LocalDate;

public record TransactionDTO(Long Id, Long accountId, String transactionType, LocalDate localDate) {
}
