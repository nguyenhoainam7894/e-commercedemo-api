package com.example.ecommercedemo.reposity;

import com.example.ecommercedemo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderReposity extends JpaRepository<Order,Long> {
}
