package com.example.ecommercedemo.controller;

//import com.example.ecommercedemo.DTO.ProductDto;
import com.example.ecommercedemo.DTO.ProductDto;
import com.example.ecommercedemo.Specification.ObjectFilter;
import com.example.ecommercedemo.entity.Product;
import com.example.ecommercedemo.reponse.RESTPagination;
import com.example.ecommercedemo.reponse.RESTResponse;
import com.example.ecommercedemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/products")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping(value = "/insert")
    public ResponseEntity<Product>insertProduct(@Valid  @RequestBody Product product){
//        return ResponseEntity.status(HttpStatus.OK).body(productService.insert(product));
        Product productDto = productService.insert(product);
        return new ResponseEntity<>(productDto,HttpStatus.CREATED);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<?>getAllList(
//        List<Product>productDtoList = productService.getProduct();
//        return new ResponseEntity<>(productDtoList,HttpStatus.CREATED);
        @RequestParam(name = "page",defaultValue = "1") int page,
        @RequestParam(name = "pageSize",defaultValue = "5") int pageSize,
        @RequestParam(name = "minPrice",defaultValue = "-1") int minPrice,
        @RequestParam(name = "maxPrice",defaultValue = "-1") int maxPrice,
        @RequestParam(name = "categoryId", defaultValue = "-1") int categoryId,
        @RequestParam(name = "name", required = false) String name
    ){
            ObjectFilter filter = ObjectFilter.ObjectFilterBuilder.anObjectFilter()
                    .withCategoryId(categoryId)
                    .withNameProduct(name)
                    .withPage(page)
                    .withMinPrice(minPrice)
                    .withMaxPrice(maxPrice)
                    .withPageSize(pageSize)
                    .build();
            Page<Product> productPage = productService.getProduct(filter);
            if (productPage.getContent().size() == 0){
                return new ResponseEntity<>(
                        new RESTResponse.Success().setMessage("Product list is empty").build(),HttpStatus.OK);
            }
            List<ProductDto> dto = new ArrayList<>();
            for (Product p : productPage.getContent()) {
                ProductDto productDTO = ProductDto.productDto(p);
                dto.add(productDTO);
            }
            return new ResponseEntity<>(
                    new RESTResponse.Success()
                            .addData(dto)
                            .setPagination(new RESTPagination(productPage.getNumber() - 1,productPage.getSize(),productPage.getTotalPages(),productPage.getTotalElements()))
                            .setStatus(HttpStatus.OK.value())
                            .build(),HttpStatus.OK
            );
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product>updateProduct(@PathVariable Long id, @RequestBody Product product){
        return  ResponseEntity.status(HttpStatus.OK).body(productService.updateProductById(id,product));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Product>deleteProduct(@PathVariable Long id){
        return  ResponseEntity.status(HttpStatus.OK).body(productService.deleteById(id));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product>findProductById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findById(id));
    }

}
