package com.example.ecommercedemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail  {
    @EmbeddedId
    private OrderDetailId id = new OrderDetailId();

    @Column(name = "product_id",updatable = false,insertable = false)
    private Long productId;

    @Column(name = "order_id",updatable = false,insertable = false)
    private int orderId;

    private int quantity;

    private long unitprice;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    @MapsId("orderId")
    @JsonIgnore
    private Order order;

    @ManyToOne()
    @JoinColumn(name="product_id")
    @MapsId("productId")
    private Product product;



}
