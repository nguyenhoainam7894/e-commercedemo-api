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
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    private String productname;

    private int unitPrice;

    private String image;

    private int quantity;

    @Column(name = "shoppingcart_id")
    private Long shoppingcartId;

    @ManyToOne()
    @JoinColumn(name = "shoppingcart_id",insertable = false,updatable = false)
    @JsonIgnore
    private ShoppingCart shoppingCart;

}
