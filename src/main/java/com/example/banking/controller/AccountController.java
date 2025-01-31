package com.example.banking.controller;

import com.example.banking.dto.AccountDTO;
import com.example.banking.dto.FundTransferDTO;
import com.example.banking.dto.TransactionDTO;
import com.example.banking.entity.AccountEntity;
import com.example.banking.services.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountServices accountServices;
    @PostMapping("/create")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
//        System.out.println("animesh---Raj"+accountDTO.getId());
//        System.out.println(accountDTO.getAcc_name());
//        System.out.println(accountDTO.getBalance());
        AccountDTO accountDto = accountServices.createAccount(accountDTO);
        return new ResponseEntity<>(accountDto, HttpStatus.CREATED);
    }
    @GetMapping("/allaccount")
    public ResponseEntity<List<AccountDTO>> getAllAccount(){
        List<AccountDTO> alllst =  accountServices.allAccount();
        return new ResponseEntity<>(alllst, HttpStatus.OK);
    }
    @GetMapping("/getacc/{id}")
    public ResponseEntity<Object> getAccByid(@PathVariable Long id){
        //AccountDTO acc = accountServices.getAccount(id);
        try {
             AccountDTO acc = accountServices.getAccount(id);
             String str = acc.toString();
            return new ResponseEntity<>(acc, HttpStatus.OK);
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("not valid id", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/upddate/{id}")
    public ResponseEntity<AccountDTO> upaccunt(@RequestBody AccountDTO accountDTO, @PathVariable Long id){
            AccountDTO acc = accountServices.updateacc(accountDTO, id);
            return new ResponseEntity<>(acc, HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public String deletebyid(@PathVariable Long id){
        accountServices.deletebyId(id);
        return "Deleted Successfully";
    }
    @PutMapping("/deposit/{id}")
    public ResponseEntity<AccountDTO> depossitmoney(@RequestBody AccountDTO accountDTO, @PathVariable Long id){
        AccountDTO accountDTO1 = accountServices.depositmoney(accountDTO, id);
        return new ResponseEntity<>(accountDTO1, HttpStatus.OK);
    }
    @PutMapping("/withdraw/{id}")
    public ResponseEntity<AccountDTO> withdrawAmount(@RequestBody AccountDTO accountDTO, @PathVariable Long id){
        AccountDTO accountDTO1 = accountServices.withdraw(accountDTO, id);
        return new ResponseEntity<>(accountDTO1, HttpStatus.OK);
    }
    @PutMapping("/fundtransfer")
    public String fundTransfer(FundTransferDTO fundTransferDTO){
         accountServices.fundTransfer(fundTransferDTO);
        return "fund transfer successfully";
    }
    @GetMapping("tranhistory/{id}")
    public List<TransactionDTO> gettranhistory(Long id){
        List<TransactionDTO> transactionDTO = accountServices.transHistory(id);
        return transactionDTO;
    }

}
