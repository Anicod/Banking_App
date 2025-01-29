package com.example.banking.exception;

import java.time.LocalDate;

public record ErrorResponse(LocalDate dt, String message, String path, String code) {
}
