package com.example.ecommercedemo.controller;

import com.example.ecommercedemo.entity.CartItem;
import com.example.ecommercedemo.entity.ShoppingCart;
import com.example.ecommercedemo.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/carts")
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;

    @PostMapping(path = "add")
    public ShoppingCart addToCart(@RequestParam(name = "accountId") Long accountId, @RequestBody CartItem cartItem){
        return shoppingCartService.Add(accountId,cartItem);
    }

    @GetMapping(path = "list")
    public List<ShoppingCart> getAllCart(){
        return shoppingCartService.getAllCart();
    }
}
