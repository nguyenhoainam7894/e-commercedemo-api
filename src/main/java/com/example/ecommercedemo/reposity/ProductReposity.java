package com.example.ecommercedemo.reposity;

import com.example.ecommercedemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductReposity extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {

}
