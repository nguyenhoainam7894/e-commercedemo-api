package com.example.ecommercedemo.DTO;

import com.example.ecommercedemo.entity.OrderDetail;
import com.example.ecommercedemo.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private int price;
    private String description;
    private String status;
    private String image;
    private Long categoryId;
    private Set<OrderDetail> orderDetailList;
    public static ProductDto productDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setStatus(product.getStatus());
        productDto.setImage(product.getImage());
        productDto.setOrderDetailList(product.getOrderDetailList());
        productDto.setCategoryId(product.getCategoryId());
        return productDto;
    }
}
