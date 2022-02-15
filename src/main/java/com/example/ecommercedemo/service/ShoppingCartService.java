package com.example.ecommercedemo.service;

import com.example.ecommercedemo.entity.CartItem;
import com.example.ecommercedemo.entity.Order;
import com.example.ecommercedemo.entity.Product;
import com.example.ecommercedemo.entity.ShoppingCart;

import java.util.HashMap;
import java.util.List;

public interface ShoppingCartService {
//    ShoppingCart getAllShoppingCart();
//    ShoppingCart getShoppingCartById();
    ShoppingCart Add(String accessToken, CartItem cartItem);
    List<ShoppingCart> getAllCart();
    ShoppingCart updateCard(String accessToken, CartItem cartItem);
    ShoppingCart deleteCart(String accessToken, CartItem cartItem);
    ShoppingCart clear(String accessToken);
    ShoppingCart getCart(String accessToken);
    Order prepareOrder(String accessToken);


}
