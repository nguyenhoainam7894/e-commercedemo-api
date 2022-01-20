package com.example.ecommercedemo.service;

import com.example.ecommercedemo.DTO.OrderDetailDto;
import com.example.ecommercedemo.DTO.OrderDto;
//import com.example.ecommercedemo.DTO.ProductDto;
import com.example.ecommercedemo.entity.Order;
import com.example.ecommercedemo.entity.OrderDetail;
import com.example.ecommercedemo.entity.OrderDetailId;
import com.example.ecommercedemo.entity.Product;
import com.example.ecommercedemo.exception.ResourceNotFoundException;
//import com.example.ecommercedemo.reposity.OrderDetailReposity;
import com.example.ecommercedemo.reposity.OrderDetailReposity;
import com.example.ecommercedemo.reposity.OrderReposity;
import com.example.ecommercedemo.reposity.ProductReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderReposity orderReposity;
    @Autowired
    ProductReposity productReposity;

    @Autowired
    OrderDetailReposity orderDetailReposity;

//    @Autowired
//    OrderDetailReposity orderDetailReposity;
    @Override
    public List<OrderDto> getOrder() {
        List<Order>orders = orderReposity.findAll();
        List<OrderDto>orderDtos=new ArrayList<>();
        for (Order order: orders) {
            orderDtos.add(OrderDto.orderDto(order));
        }
        return orderDtos;
    }

    @Override
    public OrderDto findById(Long id) {
        Order order = orderReposity.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Can not found order by id:" + id));
        return OrderDto.orderDto(order);
    }

    @Override
    public OrderDto insertOrder(Order order) {
        Order newOrder = Order.builder()
                .createdat(order.getCreatedat())
                .status(order.getStatus())
                .shipphone(order.getShipphone())
                .shipname(order.getShipname())
                .updateat(order.getUpdateat())
                .shipaddress(order.getShipaddress())
                .accountId(order.getAccountId())
                .build();

        orderReposity.save(newOrder);
        System.out.println(newOrder.getId());
                    Set<OrderDetail> listDetails = new HashSet<>();
                    for(OrderDetail od: order.getOrderDetailList()){
                        Product product = productReposity.findById(od.getId().getProductId()).get();
                        OrderDetailId orderDetailId = new OrderDetailId();

                        orderDetailId.setOrderId(newOrder.getId());
                        orderDetailId.setProductId(od.getId().getProductId());

                        OrderDetail orderDetail = new OrderDetail();

                        orderDetail.setProduct(product);
                        orderDetail.setOrder(newOrder);

                        orderDetail.setId(orderDetailId);

                        orderDetail.setQuantity(od.getQuantity());
                        orderDetail.setUnitprice(od.getUnitprice());

                        listDetails.add(orderDetail);
                        orderDetailReposity.save(orderDetail);
                        orderReposity.save(newOrder);
                    }
                    newOrder.setOrderDetailList(listDetails);
        return OrderDto.orderDto(newOrder);
    }

    @Override
    public OrderDto updateOrderById(Long id, Order order) {
        Order orderUpdate = orderReposity.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Can not found order by id:" + id));
        orderUpdate.setCreatedat(order.getCreatedat());
        orderUpdate.setUpdateat(order.getUpdateat());
        orderUpdate.setShipaddress(order.getShipaddress());
        orderUpdate.setShipname(orderUpdate.getShipname());
        orderUpdate.setShipphone(orderUpdate.getShipphone());
        orderUpdate.setStatus(order.getStatus());
        orderReposity.save(orderUpdate);
        return OrderDto.orderDto(orderUpdate);
    }

    @Override
    public OrderDto deleteOrderById(Long id) {
        Order order = orderReposity.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Can not found order by id:" + id));
        orderReposity.delete(order);
        return OrderDto.orderDto(order);
    }
}
