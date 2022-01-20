package com.example.ecommercedemo.service;

import com.example.ecommercedemo.DTO.CategoryDto;
//import com.example.ecommercedemo.DTO.ProductDto;
import com.example.ecommercedemo.entity.Category;
import com.example.ecommercedemo.entity.Product;
import com.example.ecommercedemo.exception.ResourceNotFoundException;
import com.example.ecommercedemo.reposity.CategoryReposity;
import com.example.ecommercedemo.reposity.ProductReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryReposity categoryReposity;
    @Autowired
    ProductReposity productReposity;

    @Override
    public List<CategoryDto> getCategory() {
        List<Category>categories = categoryReposity.findAll();
        List<CategoryDto>categoryDtos = new ArrayList<>();
        for (Category category: categories) {
            categoryDtos.add(CategoryDto.categoryDto(category));
        }
        return categoryDtos;
    }

    @Override
    public CategoryDto findById(Long id) {
        Category category = categoryReposity.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Not found category by id: " + id));
        return CategoryDto.categoryDto(category);
    }

    @Override
    public CategoryDto insert(Category category) {
        Category categoryInsert = categoryReposity.save(category);
        return CategoryDto.categoryDto(categoryInsert);
    }

    @Override
    public CategoryDto updateCategoryById(Long id, Category category) {
        Category categoryUpdate = categoryReposity.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Not found category by id: " + id));
        categoryUpdate.setName(category.getName());
        categoryUpdate.setProductList(category.getProductList());
        categoryReposity.save(categoryUpdate);
        return CategoryDto.categoryDto(categoryUpdate);
    }

    @Override
    public CategoryDto deleteById(Long id) {
        Category categoryDelete = categoryReposity.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Not found category by id: " + id));
        categoryReposity.delete(categoryDelete);
        return CategoryDto.categoryDto(categoryDelete);
    }

    @Override
    public CategoryDto insertProductToCategoy(Long categoryId, Long productId) {
        Product product = productReposity.findById(productId).orElseThrow(()->
                new ResourceNotFoundException("Not found product by id: " + productId));
        Category category = categoryReposity.findById(categoryId).orElseThrow(()->
                new ResourceNotFoundException("Not found category by id: " + categoryId));
        category.getProductList().add(product);
        categoryReposity.save(category);
        return CategoryDto.categoryDto(category);

    }

    @Override
    public CategoryDto removeProductFromCategory(Long categoryId, Long productId) {
        Product product = productReposity.findById(productId).orElseThrow(()->
                new ResourceNotFoundException("Not found product by id: " + productId));
        Category category = categoryReposity.findById(categoryId).orElseThrow(()->
                new ResourceNotFoundException("Not found category by id: " + categoryId));
        category.getProductList().remove(product);
        categoryReposity.save(category);
        return CategoryDto.categoryDto(category);
    }
}
