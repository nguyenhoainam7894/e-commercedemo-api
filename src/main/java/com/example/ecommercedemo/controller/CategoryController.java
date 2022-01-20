package com.example.ecommercedemo.controller;

import com.example.ecommercedemo.DTO.CategoryDto;
//import com.example.ecommercedemo.DTO.ProductDto;
import com.example.ecommercedemo.entity.Category;
import com.example.ecommercedemo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/categories")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "/list")
    public ResponseEntity<List<CategoryDto>>getAllCategory(){
//        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategory());
        List<CategoryDto>categoryDtoList = categoryService.getCategory();
        return new ResponseEntity<>(categoryDtoList,HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDto>findCategoryById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findById(id));
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<CategoryDto>insertCategory(@RequestBody Category category){
//        return ResponseEntity.status(HttpStatus.OK).body(categoryService.insert(category));
        CategoryDto categoryDto = categoryService.insert(category);
        return new ResponseEntity<>(categoryDto,HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDto>updateCategoryById(@PathVariable Long id, @RequestBody Category category){
        return  ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategoryById(id,category));
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<CategoryDto>deletecategoryById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.deleteById(id));
    }

    @PostMapping(value = "/{categoryId}/products/{productId}/add")
    public ResponseEntity<CategoryDto>insertProductToCategory(@PathVariable Long categoryId, @PathVariable Long productId){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.insertProductToCategoy(categoryId,productId));
    }

    @DeleteMapping(value = "/{categoryId}/products/{productId}/delete")
    public ResponseEntity<CategoryDto>deleteProductToCategory(@PathVariable Long categoryId, @PathVariable Long productId){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.removeProductFromCategory(categoryId,productId));
    }
}
