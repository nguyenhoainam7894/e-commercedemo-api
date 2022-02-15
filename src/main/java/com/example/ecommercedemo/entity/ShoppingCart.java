package com.example.ecommercedemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "account_id")
//    private Long accountId;
      @Column(name = "access_token")
      private String accessToken;

      @Column(name = "totalPrice")
      private int totalPrice;


    @OneToMany(mappedBy = "shoppingCart",cascade = CascadeType.ALL)
    Set<CartItem> setItem;

    @JsonIgnore
    public int getTotal(){
        Set<CartItem> list = this.getSetItem();
        if(this.getSetItem()!=null){
            int total = 0;
            for(CartItem cartiem : list){
                total +=cartiem.getQuantity() * cartiem.getUnitPrice();
            }
            return total;
        }
        return 0;
    }
    @JsonIgnore
    public void setTotalMoney(){
        this.totalPrice = getTotal();
    }
}

