package com.example.ecommercedemo.reposity;

import com.example.ecommercedemo.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemReposity extends JpaRepository<CartItem,Long> {

}
