package com.example.banking.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long acccountId;
    private double amount;
    private String transactionType;
    private LocalDate localDate;

    public Transactions(Long id, Long acccountId, double amount, String transactionType, LocalDate localDate) {
        this.id = id;
        this.acccountId = acccountId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.localDate = localDate;
    }
    public Transactions(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAcccountId() {
        return acccountId;
    }

    public void setAcccountId(Long acccountId) {
        this.acccountId = acccountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
