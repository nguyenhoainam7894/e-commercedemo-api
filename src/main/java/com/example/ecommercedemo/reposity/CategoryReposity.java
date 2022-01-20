package com.example.ecommercedemo.reposity;

import com.example.ecommercedemo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryReposity extends JpaRepository<Category, Long> {
}
