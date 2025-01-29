package com.example.banking.mapper;

import com.example.banking.dto.AccountDTO;
import com.example.banking.dto.FundTransferDTO;
import com.example.banking.entity.AccountEntity;

public class AccountMapper {
    public static AccountEntity getAccountMapper(AccountDTO accountDTO){
        AccountEntity accountEntity = new AccountEntity(accountDTO.id(), accountDTO.accName(), accountDTO.balance());
        return  accountEntity;
    }
    public static  AccountDTO getAccountDTO(AccountEntity accountEntity){
        AccountDTO accountDTO = new AccountDTO(accountEntity.getId(), accountEntity.getAcc_holder_name(), accountEntity.getBalance());
        return accountDTO;
    }

}
