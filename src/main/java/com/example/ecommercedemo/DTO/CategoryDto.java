package com.example.ecommercedemo.DTO;

import com.example.ecommercedemo.entity.Category;
import com.example.ecommercedemo.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long id;
    private String name;
    private List<Product> productList = new ArrayList<>();

    public static CategoryDto categoryDto (Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        categoryDto.setId(category.getId());
        categoryDto.setProductList(category.getProductList());
        return categoryDto;
    }

}
