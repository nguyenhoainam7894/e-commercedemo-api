package com.example.ecommercedemo.service;

import com.example.ecommercedemo.entity.*;
import com.example.ecommercedemo.reposity.CartItemReposity;
import com.example.ecommercedemo.reposity.ProductReposity;
import com.example.ecommercedemo.reposity.ShoppingCartReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

//import com.example.ecommercedemo.entity.ShoppingCart;
@Service
public class ShoppingCartServiceImpl implements  ShoppingCartService{
    @Autowired
    ShoppingCartReposity shoppingCartReposity;

    @Autowired
    ProductReposity productReposity;

    @Autowired
    CartItemReposity cartItemReposity;

    @Override
    public ShoppingCart Add(String accessToken, CartItem cartItem) {
        ShoppingCart exist = shoppingCartReposity.findCartByAccessToken(accessToken);
        Product product = productReposity.getById(cartItem.getProductId());
        cartItem.setProductname(product.getName());
        cartItem.setImage(product.getImage());
        cartItem.setUnitPrice(product.getPrice());
        if(exist != null){
            Set<CartItem>listCartItem = exist.getSetItem();
            for (CartItem item:listCartItem) {
                if(item.getProductId() == cartItem.getProductId()){
                    item.setQuantity(item.getQuantity()+1);
                    exist.setTotalMoney();
                    return shoppingCartReposity.save(exist);
                }
            }
            listCartItem.add(cartItem);
            cartItem.setShoppingcartId(exist.getId());
            exist.setSetItem(listCartItem);
            exist.setTotalMoney();
            return shoppingCartReposity.save(exist);
        }
        ShoppingCart newCart = new ShoppingCart();
        ShoppingCart save = shoppingCartReposity.save(newCart);
        newCart.setAccessToken(accessToken);
        Set<CartItem> newCartItem = new HashSet<>();
        newCartItem.add(cartItem);
        cartItem.setShoppingcartId(save.getId());
        save.setSetItem(newCartItem);
//        if(exist==null){
//            exist.setTotalPrice(cartItem.getUnitPrice());
//        }
        save.setTotalPrice(cartItem.getUnitPrice());
        return shoppingCartReposity.save(newCart);
    }

    @Override
    public List<ShoppingCart> getAllCart() {
        return shoppingCartReposity.findAll();
    }

    @Override
    public ShoppingCart updateCard(String accessToken, CartItem cartItem) {
        ShoppingCart exist = shoppingCartReposity.findCartByAccessToken(accessToken);
        Set<CartItem>listCartItem = exist.getSetItem();
        for (CartItem item: listCartItem) {
            if(item.getProductId()==cartItem.getProductId()){
                item.setQuantity(cartItem.getQuantity());
                exist.setTotalMoney();
                cartItemReposity.save(item);
            }
        }
        return exist;
    }

    @Override
    public ShoppingCart deleteCart(String accessToken, CartItem cartItem) {
        ShoppingCart exist = shoppingCartReposity.findCartByAccessToken(accessToken);
//        Set<CartItem>listCartItem = exist.getSetItem();
        for (CartItem item: exist.getSetItem()) {
            if(item.getProductId()==cartItem.getProductId()){
                exist.getSetItem().remove(item);
                exist.setTotalMoney();
                cartItemReposity.delete(item);
            }
        }
        exist.setTotalMoney();
        return shoppingCartReposity.save(exist);
    }

    @Override
    public ShoppingCart clear(String accessToken) {
        ShoppingCart exist = shoppingCartReposity.findCartByAccessToken(accessToken);
//        List<CartItem>cartItems = cartItemReposity.findCartItemsByShoppingCartId(exist.getId());
//        for (CartItem item: cartItems) {
//            exist.setTotalMoney();
//            cartItemReposity.delete(item);
//        }
//        return shoppingCartReposity.save(exist);
        if(exist!=null){
            shoppingCartReposity.delete(exist);
            exist.setTotalMoney();
            return shoppingCartReposity.save(exist);
        }
        return exist;
    }

    @Override
    public ShoppingCart getCart(String accessToken) {
        ShoppingCart exist = shoppingCartReposity.findCartByAccessToken(accessToken);
        return exist;
    }

    @Override
    public Order prepareOrder(String accessToken) {
        ShoppingCart shoppingCart = shoppingCartReposity.findCartByAccessToken(accessToken);
        Order order = new Order();
        Set<OrderDetail>orderDetails = new HashSet<>();
        for (CartItem cartItem: shoppingCart.getSetItem()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setUnitprice(cartItem.getUnitPrice());
            orderDetail.setProductId(cartItem.getProductId());
            orderDetails.add(orderDetail);
        }
        order.setStatus("PENDING");
        order.setCreatedat(LocalDate.now());
        order.setTotalPrice(shoppingCart.getTotalPrice());
        order.setOrderDetailList(orderDetails);

        return order;
    }

//    @Override
//    public int calculateToTalPrice(String accessToken) {
//        ShoppingCart exist = shoppingCartReposity.findCartByAccessToken(accessToken);
//        Set<CartItem>listCartItem = exist.getSetItem();
//        int totalPrice= 0;
//        for (CartItem item: listCartItem) {
//            totalPrice += (item.getQuantity()*item.getUnitPrice());
//        }
//        return totalPrice;
//    }
}
