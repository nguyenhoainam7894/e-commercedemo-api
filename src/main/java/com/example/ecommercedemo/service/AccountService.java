package com.example.ecommercedemo.service;

import com.example.ecommercedemo.DTO.AccountDto;
//import com.example.ecommercedemo.Specification.FilterField;
import com.example.ecommercedemo.entity.Account;

import java.util.List;

public interface AccountService {
//    List<AccountDto> getAccount(FilterField filter);
    List<AccountDto> getAccount();
    AccountDto findById(Long id);
    AccountDto insert(Account account);
    AccountDto updateUserById(Long id, Account account);
    AccountDto deleteById(Long id);
    AccountDto insertOrderToAccount(Long accountId, Long orderId);
    AccountDto removeOrderFromAccount(Long accountId, Long orderId);

}
