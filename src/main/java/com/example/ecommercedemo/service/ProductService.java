package com.example.ecommercedemo.service;

//import com.example.ecommercedemo.DTO.ProductDto;
import com.example.ecommercedemo.Specification.ObjectFilter;
import com.example.ecommercedemo.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Page<Product> getProduct(ObjectFilter filter);
    Product findById(Long id);
    Product insert(Product product);
    Product updateProductById(Long id, Product product);
    Product deleteById(Long id);
}
