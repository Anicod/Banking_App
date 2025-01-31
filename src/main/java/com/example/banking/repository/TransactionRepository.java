package com.example.banking.repository;

import com.example.banking.entity.Transactions;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {
    public List<Transactions> findByAcccountIdOrderByLocalDateDesc(Long acccountId);
}
