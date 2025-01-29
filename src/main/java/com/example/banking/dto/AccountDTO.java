package com.example.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public record AccountDTO(Long id, String accName, Double balance) {}

//    public AccountDTO(Long id, String acc_name, Double balance) {
//        this.id = id;
//        this.acc_name = acc_name;
//        this.balance = balance;
//    }
//    public AccountDTO(){
//
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getAcc_name() {
//        return acc_name;
//    }
//
//    public void setAcc_name(String acc_name) {
//        this.acc_name = acc_name;
//    }
//
//    public Double getBalance() {
//        return balance;
//    }
//
//    public void setBalance(Double balance) {
//        this.balance = balance;
//    }
