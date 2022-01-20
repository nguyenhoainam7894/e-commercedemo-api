package com.example.ecommercedemo.DTO;

import com.example.ecommercedemo.entity.Account;
import com.example.ecommercedemo.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private List<Order> orders = new ArrayList<>();

    public static AccountDto accountDto (Account account){
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setUsername(account.getUsername());
        accountDto.setEmail(account.getEmail());
        accountDto.setOrders(account.getOrders());
        return accountDto;
    }
}
