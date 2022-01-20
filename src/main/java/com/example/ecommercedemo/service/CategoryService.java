package com.example.ecommercedemo.service;

import com.example.ecommercedemo.DTO.CategoryDto;
//import com.example.ecommercedemo.DTO.ProductDto;
import com.example.ecommercedemo.entity.Category;
import com.example.ecommercedemo.entity.Product;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getCategory();
    CategoryDto findById(Long id);
    CategoryDto insert(Category category);
    CategoryDto updateCategoryById(Long id, Category category);
    CategoryDto deleteById(Long id);
    CategoryDto insertProductToCategoy(Long categoryId, Long prooductId);
    CategoryDto removeProductFromCategory(Long categoryId, Long productId);
}
