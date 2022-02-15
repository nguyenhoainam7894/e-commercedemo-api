package com.example.ecommercedemo.reposity;

import com.example.ecommercedemo.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemReposity extends JpaRepository<CartItem,Long> {
    List<CartItem> findCartItemsByShoppingCartId(Long id);
}
