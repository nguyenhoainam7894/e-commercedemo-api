package com.example.ecommercedemo.service;

import com.example.ecommercedemo.entity.CartItem;
import com.example.ecommercedemo.entity.Product;
import com.example.ecommercedemo.entity.ShoppingCart;
import com.example.ecommercedemo.reposity.ProductReposity;
import com.example.ecommercedemo.reposity.ShoppingCartReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

//import com.example.ecommercedemo.entity.ShoppingCart;
@Service
public class ShoppingCartServiceImpl implements  ShoppingCartService{
    @Autowired
    ShoppingCartReposity shoppingCartReposity;

    @Autowired
    ProductReposity productReposity;

    @Override
    public ShoppingCart Add(Long accountId, CartItem cartItem) {
        ShoppingCart exist = shoppingCartReposity.findCartByAccountId(accountId);
        Product product = productReposity.getById(cartItem.getProductId());
        cartItem.setProductname(product.getName());
        cartItem.setImage(product.getImage());
        cartItem.setUnitPrice(product.getPrice());
        if(exist != null){
            Set<CartItem>listCartItem = exist.getSetItem();
            for (CartItem item:listCartItem) {
                if(item.getProductId() == cartItem.getProductId()){
                    item.setQuantity(item.getQuantity()+1);
                    return shoppingCartReposity.save(exist);
                }
            }
            listCartItem.add(cartItem);
            cartItem.setShoppingcartId(exist.getId());
            exist.setSetItem(listCartItem);
            return shoppingCartReposity.save(exist);
        }
        ShoppingCart newCart = new ShoppingCart();
        ShoppingCart save = shoppingCartReposity.save(newCart);
        newCart.setAccountId(accountId);
        Set<CartItem> newCartItem = new HashSet<>();
        newCartItem.add(cartItem);
        cartItem.setShoppingcartId(save.getId());
        save.setSetItem(newCartItem);
        return shoppingCartReposity.save(newCart);
    }

    @Override
    public List<ShoppingCart> getAllCart() {
        return shoppingCartReposity.findAll();
    }
}
