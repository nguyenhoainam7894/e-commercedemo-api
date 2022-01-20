package com.example.ecommercedemo.controller;

import com.example.ecommercedemo.DTO.OrderDto;
import com.example.ecommercedemo.entity.Order;
import com.example.ecommercedemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping(value = "/list")
    public ResponseEntity<List<OrderDto>>getAllOrder(){
        List<OrderDto>orderDtoList=orderService.getOrder();
        return new ResponseEntity<>(orderDtoList, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDto>getOrderById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findById(id));
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<OrderDto>insertOrder(@RequestBody Order order){
        OrderDto orderDto = orderService.insertOrder(order);
        return new ResponseEntity<>(orderDto,HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<OrderDto>updateOrderById(@PathVariable Long id, @RequestBody Order order){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrderById(id,order));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<OrderDto>deleteOrderByID(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.deleteOrderById(id));
    }
}
