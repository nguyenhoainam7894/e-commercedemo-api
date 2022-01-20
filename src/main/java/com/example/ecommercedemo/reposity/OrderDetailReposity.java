package com.example.ecommercedemo.reposity;

import com.example.ecommercedemo.entity.OrderDetail;
import com.example.ecommercedemo.entity.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailReposity extends JpaRepository<OrderDetail, OrderDetailId> {
}
