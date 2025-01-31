package com.example.banking.services;

import com.example.banking.dto.AccountDTO;
import com.example.banking.dto.FundTransferDTO;
import com.example.banking.dto.TransactionDTO;
import com.example.banking.entity.AccountEntity;

import java.util.List;

public interface AccountServices {
    public AccountDTO createAccount(AccountDTO acc);
    public List<AccountDTO> allAccount();
    public  AccountDTO getAccount(Long id);
    public AccountDTO updateacc(AccountDTO acc, Long id);
    public void deletebyId(Long id);
    public AccountDTO depositmoney(AccountDTO accountDTO, Long id);
    public AccountDTO withdraw(AccountDTO accountDTO, Long id);
    public void fundTransfer(FundTransferDTO fundTransferDTO);
    public List<TransactionDTO> transHistory(Long id);
}