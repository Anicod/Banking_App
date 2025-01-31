package com.example.banking.services.impl;

import com.example.banking.dto.AccountDTO;
import com.example.banking.dto.FundTransferDTO;
import com.example.banking.dto.TransactionDTO;
import com.example.banking.entity.AccountEntity;
import com.example.banking.entity.Transactions;
import com.example.banking.exception.AccountNotFoundException;
import com.example.banking.mapper.AccountMapper;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.TransactionRepository;
import com.example.banking.services.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountServices {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    public AccountDTO createAccount(AccountDTO accountDTO){
        AccountEntity accountEntity = AccountMapper.getAccountMapper(accountDTO);
        AccountEntity accen = accountRepository.save(accountEntity);
        AccountDTO accountDTO1 = AccountMapper.getAccountDTO(accen);
        return  accountDTO1;
    }
    public  List<AccountDTO> allAccount(){
        List<AccountEntity> accountEntitylst = accountRepository.findAll();
        List<AccountDTO> lst = new ArrayList<>();
        for(AccountEntity lst1 : accountEntitylst){
            lst.add(AccountMapper.getAccountDTO(lst1));
        }
        return lst;
    }
    public AccountDTO getAccount(Long id){
        List<AccountEntity> acc = accountRepository.findAll();
        AccountDTO accountDTO = null;
        for(AccountEntity lst1 : acc){
            if(lst1.getId().equals(id)){
                return AccountMapper.getAccountDTO(lst1);
            }
        }
        return accountDTO;

    }
    public AccountDTO updateacc(AccountDTO accountDTO, Long id){
        AccountEntity accountEntity = accountRepository.findById(id).orElseThrow(()->new AccountNotFoundException("Account not found--"+id));
        accountEntity.setAcc_holder_name(accountDTO.accName());
        accountEntity.setBalance(accountDTO.balance());
        AccountDTO accountDTO1 = AccountMapper.getAccountDTO(accountRepository.save(accountEntity));
        return accountDTO1;
    }
    public void deletebyId(Long id){
        accountRepository.deleteById(id);
    }
    public AccountDTO depositmoney(AccountDTO accountDTO, Long id){
        AccountEntity accountEntity = accountRepository.findById(id).orElseThrow(()->new AccountNotFoundException("id not found"));
        accountEntity.setBalance(accountDTO.balance()+accountEntity.getBalance());
        Transactions transactions = new Transactions();
        transactions.setAcccountId(id);
        transactions.setAmount(accountDTO.balance());
        transactions.setTransactionType("Deposit");
        transactions.setLocalDate(LocalDate.now());
        transactionRepository.save(transactions);
        AccountDTO accountDTO1 = AccountMapper.getAccountDTO(accountRepository.save(accountEntity));
        return  accountDTO1;

    }
    public AccountDTO withdraw(AccountDTO accountDTO, Long id){
        AccountEntity accountEntity = accountRepository.findById(id).orElseThrow(()->new AccountNotFoundException("id not found"));
        Double amount = accountEntity.getBalance()-accountDTO.balance();
        accountEntity.setBalance(amount);
        AccountDTO accountDTO1 = AccountMapper.getAccountDTO(accountRepository.save(accountEntity));
        Transactions transactions = new Transactions();
        transactions.setAcccountId(id);
        transactions.setAmount(accountDTO.balance());
        transactions.setTransactionType("Withdrw");
        transactions.setLocalDate(LocalDate.now());
        transactionRepository.save(transactions);
        return accountDTO1;

    }

    @Override
    public void fundTransfer(FundTransferDTO fundTransferDTO){
        AccountEntity fromaccountEntity = accountRepository.findById(fundTransferDTO.fromAcc()).orElseThrow(()->new AccountNotFoundException("Account not exsist"));
        AccountEntity toaccountEntity = accountRepository.findById(fundTransferDTO.toAcc()).orElseThrow(()->new AccountNotFoundException("Account not exsit"));
        fromaccountEntity.setBalance(fromaccountEntity.getBalance()-fundTransferDTO.amount());
        toaccountEntity.setBalance(toaccountEntity.getBalance()+fundTransferDTO.amount());
        accountRepository.save(fromaccountEntity);
        accountRepository.save(toaccountEntity);
        Transactions transactions = new Transactions();
        transactions.setAcccountId(fundTransferDTO.fromAcc());
        transactions.setAmount(fundTransferDTO.amount());
        transactions.setTransactionType("TRANSFER");
        transactions.setLocalDate(LocalDate.now());
        transactionRepository.save(transactions);
    }

    @Override
    public List<TransactionDTO> transHistory(Long id) {
        List<Transactions> lst = transactionRepository.findByAcccountIdOrderByLocalDateDesc(id);
        List<TransactionDTO> transactionDTO = lst.stream().map((tras)->convert(tras)).collect(Collectors.toList());
        return transactionDTO;

    }
    public TransactionDTO convert(Transactions transactions){
        TransactionDTO transactionDTO = new TransactionDTO(transactions.getId(), transactions.getAcccountId(), transactions.getTransactionType(), transactions.getLocalDate());
        return transactionDTO;
    }
}
