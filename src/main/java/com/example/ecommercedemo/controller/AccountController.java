package com.example.ecommercedemo.controller;

import com.example.ecommercedemo.DTO.AccountDto;
import com.example.ecommercedemo.DTO.CategoryDto;
//import com.example.ecommercedemo.DTO.ProductDto;
//import com.example.ecommercedemo.Specification.FilterField;
import com.example.ecommercedemo.entity.Account;
import com.example.ecommercedemo.entity.Category;
import com.example.ecommercedemo.entity.Product;
import com.example.ecommercedemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class AccountController {
    @Autowired
    AccountService accountService;

//    @GetMapping(value = "/list")
//    public ResponseEntity<List<AccountDto>>getAllAccount(
//            @RequestParam(name ="name") String name,
//            @RequestParam(name ="email") String eamil,
//            @RequestParam(name ="page") int page,
//            @RequestParam(name ="pageSize") int pageSize
//    ){
//        FilterField accountFilter = FilterField.FilterFieldBuilder.aFilterField()
//                .withEmail(eamil)
//                .withPage(page)
//                .withPageSize(pageSize)
//                .withName(name)
//                .build();
//        List<AccountDto> accountDtos = accountService.getAccount(accountFilter);
//        return new ResponseEntity<>(accountDtos,HttpStatus.CREATED);
//    }
    @GetMapping(value = "/list")
    public ResponseEntity<List<AccountDto>>getAllAccount(){
        List<AccountDto> accountDtos = accountService.getAccount();
        return new ResponseEntity<>(accountDtos,HttpStatus.CREATED);
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<AccountDto>insertAccount(@RequestBody Account account){
        AccountDto accountDto = accountService.insert(account);
        return new ResponseEntity<>(accountDto,HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AccountDto>updateAccount(@PathVariable Long id, @RequestBody Account account){
        return  ResponseEntity.status(HttpStatus.OK).body(accountService.updateUserById(id,account));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<AccountDto>deleteAccount(@PathVariable Long id){
        return  ResponseEntity.status(HttpStatus.OK).body(accountService.deleteById(id));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountDto>findAccountById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.findById(id));
    }
    @PostMapping(value = "/{accountId}/orders/{orderId}/add")
    public ResponseEntity<AccountDto>insertOrderToAccount(@PathVariable Long accountId, @PathVariable Long orderId){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.insertOrderToAccount(accountId,orderId));
    }
}
