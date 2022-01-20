package com.example.ecommercedemo.service;

import com.example.ecommercedemo.DTO.AccountDto;
import com.example.ecommercedemo.DTO.OrderDto;
import com.example.ecommercedemo.entity.Account;
import com.example.ecommercedemo.entity.Order;

import java.util.List;

public interface OrderService {

    List<OrderDto> getOrder();
    OrderDto findById(Long id);
    OrderDto insertOrder(Order order);
    OrderDto updateOrderById(Long id, Order order);
    OrderDto deleteOrderById(Long id);
}
