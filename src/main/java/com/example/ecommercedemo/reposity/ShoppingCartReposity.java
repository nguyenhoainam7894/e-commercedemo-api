package com.example.ecommercedemo.reposity;

import com.example.ecommercedemo.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartReposity extends JpaRepository<ShoppingCart,Long> {
    ShoppingCart findCartByAccessToken(String accesstoken);
}
