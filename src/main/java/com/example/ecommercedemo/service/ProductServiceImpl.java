package com.example.ecommercedemo.service;

//import com.example.ecommercedemo.DTO.ProductDto;
import com.example.ecommercedemo.Specification.ObjectFilter;
import com.example.ecommercedemo.Specification.ProductSpecification;
import com.example.ecommercedemo.Specification.SearchCriteria;
import com.example.ecommercedemo.entity.Product;
import com.example.ecommercedemo.exception.ResourceNotFoundException;
import com.example.ecommercedemo.reposity.ProductReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductReposity productReposity;


    @Override
    public Page<Product> getProduct(ObjectFilter filter) {
        Specification spec = Specification.where(null);

        PageRequest paging = PageRequest.of(filter.getPage() - 1, filter.getPageSize());
        if (filter.getNameProduct() != null && filter.getNameProduct().length() > 0){
            spec = spec.and(new ProductSpecification(new SearchCriteria(ObjectFilter.NAME, "=",filter.getNameProduct())));
        }
        if (filter.getCategoryId() > 0){
            spec = spec.and(new ProductSpecification(new SearchCriteria(ObjectFilter.ID,"=",filter.getCategoryId())));
        }
        if (filter.getMaxPrice() > 0){
            spec = spec.and(new ProductSpecification(new SearchCriteria(ObjectFilter.MAX_PRICE,"<=",filter.getMaxPrice())));
        }
        if (filter.getMinPrice() > 0){
            spec = spec.and(new ProductSpecification(new SearchCriteria(ObjectFilter.MIN_PRICE,">=",filter.getMinPrice())));
        }
        if (filter.getId() > 0){
            spec = spec.and(new ProductSpecification(new SearchCriteria(ObjectFilter.ID,"=",filter.getId())));
        }
//        List<Product> products = productReposity.findAll();
//        List<ProductDto>productDtos = new ArrayList<>();
//        for (Product product:products) {
//            productDtos.add(ProductDto.productDto(product));
//        }
        return productReposity.findAll(spec,paging);
    }

    @Override
    public Product findById(Long id) {
        Product product = productReposity.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product not found with id :" + id));
//        return ProductDto.productDto(product);
        return product;
    }

//    @Override
//    public Product insert(Product product) {
//        Product productInsert = productReposity.save(product);
////        return ProductDto.productDto(productInsert);
//            return productInsert;
//    }
//
//    @Override
//    public Product updateProductById(Long id, Product product) {
//        Product productUpdate = productReposity.findById(id).orElseThrow(()->
//                new ResourceNotFoundException("Product not found with id :" + id));
//        productUpdate.setName(product.getName());
//        productUpdate.setPrice(product.getPrice());
//        productUpdate.setDescription(product.getDescription());
//        productReposity.save(productUpdate);
////        return ProductDto.productDto(productUpdate);
//            return productUpdate;
//    }
//
//    @Override
//    public Product deleteById(Long id) {
//        Product productDelete = productReposity.findById(id).orElseThrow(()->
//                new ResourceNotFoundException("Product not found with id :" + id));
//        productReposity.delete(productDelete);
////        return ProductDto.productDto(productDelete);
//            return productDelete;
//    }

    @Override
    public Product insert(Product product) {
        return productReposity.save(product);
    }

    @Override
    public Product updateProductById(Long id, Product product) {
        Product productUpdate = productReposity.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Product not found with id :" + id));
        productUpdate.setName(product.getName());
        productUpdate.setPrice(product.getPrice());
        productUpdate.setDescription(product.getDescription());
        productUpdate.setImage(product.getImage());
        productUpdate.setStatus(product.getStatus());
        productUpdate.setCategoryId(product.getCategoryId());
        productReposity.save(productUpdate);
        return productUpdate;
    }

    @Override
    public Product deleteById(Long id) {
        Product productDelete = productReposity.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Product not found with id :" + id));
        productReposity.delete(productDelete);
        return productDelete;
    }
}
