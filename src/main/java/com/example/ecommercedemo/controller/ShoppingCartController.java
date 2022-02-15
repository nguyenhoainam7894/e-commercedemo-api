package com.example.ecommercedemo.controller;

import com.example.ecommercedemo.entity.CartItem;
import com.example.ecommercedemo.entity.Order;
import com.example.ecommercedemo.entity.Product;
import com.example.ecommercedemo.entity.ShoppingCart;
import com.example.ecommercedemo.service.ShoppingCartService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/carts")
@CrossOrigin("*")
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;

    @PostMapping(path = "add")
    public ResponseEntity<ShoppingCart> addToCart(@RequestParam(name = "access_token") String accessToken, @RequestBody CartItem cartItem){
        return new ResponseEntity<>(shoppingCartService.Add(accessToken,cartItem), HttpStatus.CREATED);
    }

    @GetMapping(path = "list")
    public ResponseEntity<List<ShoppingCart>> getAllCart(){
        return ResponseEntity.status(HttpStatus.OK).body(shoppingCartService.getAllCart());
    }

    @GetMapping(path = "findcart")
    public ResponseEntity<ShoppingCart> getCart(@RequestParam(name = "access_token") String accessToken){
        return ResponseEntity.status(HttpStatus.OK).body(shoppingCartService.getCart(accessToken));
    }

    @PutMapping(path = "update")
    public ResponseEntity<ShoppingCart>  updateCart(@RequestParam(name = "access_token") String accessToken, @RequestBody CartItem cartItem){
        return ResponseEntity.status(HttpStatus.OK).body(shoppingCartService.updateCard(accessToken, cartItem));
    }

    @DeleteMapping(path = "delete")
    public ResponseEntity<ShoppingCart>  deleteCart(@RequestParam(name = "access_token") String accessToken, @RequestBody CartItem cartItem){
        return ResponseEntity.status(HttpStatus.OK).body(shoppingCartService.deleteCart(accessToken,cartItem));
    }

    @DeleteMapping(path = "clear")
    public ResponseEntity<ShoppingCart>  clearCart(@RequestParam(name = "access_token") String accessToken){
        return ResponseEntity.status(HttpStatus.OK).body(shoppingCartService.clear(accessToken));
    }

    @PostMapping(path = "submit")
    public ResponseEntity<Order>  prepareOrder(@RequestParam(name = "access_token") String accessToken){
        return ResponseEntity.status(HttpStatus.OK).body(shoppingCartService.prepareOrder(accessToken));
    }
}
