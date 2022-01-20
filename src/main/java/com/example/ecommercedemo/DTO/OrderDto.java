package com.example.ecommercedemo.DTO;

import com.example.ecommercedemo.entity.Account;
import com.example.ecommercedemo.entity.Order;
import com.example.ecommercedemo.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private LocalDate createdat;
    private LocalDate updateat;
    private String status;
    private String shipname;
    private String shipaddress;
    private Long shipphone;
    private Long accountId;
    private Account account;
    private Set<OrderDetail> orderDetailList;

    public static OrderDto orderDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setCreatedat(order.getCreatedat());
        orderDto.setUpdateat(order.getUpdateat());
        orderDto.setStatus(order.getStatus());
        orderDto.setShipname(order.getShipname());
        orderDto.setShipaddress(order.getShipaddress());
        orderDto.setShipphone(order.getShipphone());
        orderDto.setAccount(order.getAccount());
        orderDto.setOrderDetailList(order.getOrderDetailList());
        orderDto.setAccountId(order.getAccountId());
        return  orderDto;
    }
}
