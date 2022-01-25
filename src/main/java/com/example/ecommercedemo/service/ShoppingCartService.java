package com.example.ecommercedemo.service;

import com.example.ecommercedemo.entity.CartItem;
import com.example.ecommercedemo.entity.Product;
import com.example.ecommercedemo.entity.ShoppingCart;

import java.util.HashMap;
import java.util.List;

public interface ShoppingCartService {
//    ShoppingCart getAllShoppingCart();
//    ShoppingCart getShoppingCartById();
    ShoppingCart Add(Long accountId, CartItem cartItem);
    List<ShoppingCart> getAllCart();


}
