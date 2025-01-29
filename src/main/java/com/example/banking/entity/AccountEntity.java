package com.example.banking.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "accounts")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "acc_name")
    private String acc_holder_name;
    private Double balance;
    //private Integer version;
    public AccountEntity(Long id, String acc_holder_name, Double balance) {
        this.id = id;
        this.acc_holder_name = acc_holder_name;
        this.balance = balance;
    }
    public AccountEntity(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcc_holder_name() {
        return acc_holder_name;
    }

    public void setAcc_holder_name(String acc_holder_name) {
        this.acc_holder_name = acc_holder_name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
