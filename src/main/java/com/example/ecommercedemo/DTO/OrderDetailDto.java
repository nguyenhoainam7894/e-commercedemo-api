package com.example.ecommercedemo.DTO;

import com.example.ecommercedemo.entity.Order;
import com.example.ecommercedemo.entity.OrderDetail;
import com.example.ecommercedemo.entity.OrderDetailId;
import com.example.ecommercedemo.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
    private OrderDetailId id;
    private Order order;
    private Product product;
    private int quantity;
    private long unitprice;

    public static OrderDetailDto orderDetailDtoDto(OrderDetail orderDetail){
        OrderDetailDto orderDetailDto = new OrderDetailDto();
        orderDetailDto.setId(orderDetail.getId());
        orderDetailDto.setOrder(orderDetail.getOrder());
        orderDetailDto.setProduct(orderDetail.getProduct());
        orderDetailDto.setQuantity(orderDetail.getQuantity());
        orderDetailDto.setUnitprice(orderDetail.getUnitprice());
        return orderDetailDto;
    }
}
