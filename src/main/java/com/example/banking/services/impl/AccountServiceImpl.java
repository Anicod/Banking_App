package com.example.banking.services.impl;

import com.example.banking.dto.AccountDTO;
import com.example.banking.dto.FundTransferDTO;
import com.example.banking.entity.AccountEntity;
import com.example.banking.exception.AccountNotFoundException;
import com.example.banking.mapper.AccountMapper;
import com.example.banking.repository.AccountRepository;
import com.example.banking.services.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountServices {
    @Autowired
    private AccountRepository accountRepository;
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
        AccountDTO accountDTO1 = AccountMapper.getAccountDTO(accountRepository.save(accountEntity));
        return  accountDTO1;

    }
    public AccountDTO withdraw(AccountDTO accountDTO, Long id){
        AccountEntity accountEntity = accountRepository.findById(id).orElseThrow(()->new AccountNotFoundException("id not found"));
        Double amount = accountEntity.getBalance()-accountDTO.balance();
        accountEntity.setBalance(amount);
        AccountDTO accountDTO1 = AccountMapper.getAccountDTO(accountRepository.save(accountEntity));
        return accountDTO1;

    }

    @Override
    public List<AccountDTO> fundTransfer(FundTransferDTO fundTransferDTO) {
        AccountServiceImpl accountService = new AccountServiceImpl();
        accountService.transfer(fundTransferDTO);
        AccountEntity accountEntity = accountRepository.findById(fundTransferDTO.fromAcc()).orElseThrow(()->new AccountNotFoundException("account not exsit"));
        AccountEntity accountEntity1 = accountRepository.findById(fundTransferDTO.toAcc()).orElseThrow(()->new AccountNotFoundException("account not exsist"));
        List<AccountDTO> al = new ArrayList<>();
        al.add(AccountMapper.getAccountDTO(accountEntity));
        al.add(AccountMapper.getAccountDTO(accountEntity1));
        return al;
    }
    public void transfer(FundTransferDTO fundTransferDTO){
        AccountEntity accountEntity = accountRepository.findById(fundTransferDTO.fromAcc()).orElseThrow(()->new AccountNotFoundException("Account not exsist"));
        AccountEntity accountEntity1 = accountRepository.findById(fundTransferDTO.toAcc()).orElseThrow(()->new AccountNotFoundException("Account not exsit"));
        accountEntity.setBalance(accountEntity.getBalance()-fundTransferDTO.amount());
        accountEntity1.setBalance(accountEntity1.getBalance()+fundTransferDTO.amount());
        accountRepository.save(accountEntity);
        accountRepository.save(accountEntity1);
    }
}
