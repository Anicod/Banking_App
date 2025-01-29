package com.example.banking.dto;

public record FundTransferDTO(Long fromAcc, Long toAcc, Long amount) {
}
