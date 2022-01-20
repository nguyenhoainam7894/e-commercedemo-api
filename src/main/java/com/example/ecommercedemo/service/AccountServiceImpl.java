package com.example.ecommercedemo.service;

import com.example.ecommercedemo.DTO.AccountDto;
//import com.example.ecommercedemo.DTO.ProductDto;
//import com.example.ecommercedemo.Specification.AccountSpecification;
//import com.example.ecommercedemo.Specification.FilterField;
//import com.example.ecommercedemo.Specification.SearchCriteria;
import com.example.ecommercedemo.entity.Account;
import com.example.ecommercedemo.entity.Category;
import com.example.ecommercedemo.entity.Order;
import com.example.ecommercedemo.entity.Product;
import com.example.ecommercedemo.exception.ResourceNotFoundException;
import com.example.ecommercedemo.reposity.AccountReposity;
import com.example.ecommercedemo.reposity.OrderReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    AccountReposity accountReposity;

    @Autowired
    OrderReposity orderReposity;

//    @Override
////    public List<AccountDto> getAccount(FilterField field) {
////        List<Account>accounts = accountReposity.findAll();
////        Specification spe = Specification.where(null);
////        if(field.getName() != null && field.getName().length() > 0){
////            spe = spe.and(new AccountSpecification(new SearchCriteria("firstName",":",field.getName())));
////        }
////        List<AccountDto> accountDtos = new ArrayList<>();
////        for (Account account:accounts) {
////            accountDtos.add(AccountDto.accountDto(account));
////        }
////        return accountDtos;
////    }

    @Override
    public List<AccountDto> getAccount() {
        List<Account>accounts = accountReposity.findAll();
        List<AccountDto> accountDtos = new ArrayList<>();
        for (Account account:accounts) {
            accountDtos.add(AccountDto.accountDto(account));
        }
        return accountDtos;
    }

    @Override
    public AccountDto findById(Long id) {
        Account account = accountReposity.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Cannot found account by id: "+ id));
        return AccountDto.accountDto(account);
    }

    @Override
    public AccountDto insert(Account account) {
        return AccountDto.accountDto(accountReposity.save(account));
    }

    @Override
    public AccountDto updateUserById(Long id, Account account) {
        Account accountUpdate = accountReposity.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Cannot found account by id: "+ id));
        accountUpdate.setUsername(account.getUsername());
        accountUpdate.setEmail(account.getEmail());
        accountUpdate.setPassword(account.getPassword());
        accountReposity.save(accountUpdate);
        return AccountDto.accountDto(accountUpdate);
    }

    @Override
    public AccountDto deleteById(Long id) {
        Account accountUpdate = accountReposity.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Cannot found account by id: "+ id));
        accountReposity.delete(accountUpdate);
        return AccountDto.accountDto(accountUpdate);
    }

    @Override
    public AccountDto insertOrderToAccount(Long accountId, Long orderId) {
        Order order = orderReposity.findById(orderId).orElseThrow(()->
                new ResourceNotFoundException("Can not found order by id" + orderId));
        Account account = accountReposity.findById(accountId).orElseThrow(()->
                new ResourceNotFoundException("Not found category by id: " + accountId));
        account.getOrders().add(order);
        accountReposity.save(account);
        return  AccountDto.accountDto(account);
    }

    @Override
    public AccountDto removeOrderFromAccount(Long accountId, Long orderId) {
        Order order = orderReposity.findById(orderId).orElseThrow(()->
                new ResourceNotFoundException("Can not found order by id" + orderId));
        Account account = accountReposity.findById(accountId).orElseThrow(()->
                new ResourceNotFoundException("Not found category by id: " + accountId));
        account.getOrders().remove(order);
        accountReposity.save(account);
        return AccountDto.accountDto(account);
    }
}
