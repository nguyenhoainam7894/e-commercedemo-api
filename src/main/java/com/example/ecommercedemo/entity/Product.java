package com.example.ecommercedemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true,length = 200)
    @NotBlank(message = "Name is mandatory")
    @NotNull(message = "Name must be not null")
    private String name;

    @Column(nullable = false,unique = false,length = 200)
//    @NotBlank(message = "Price is mandatory")
    @NotNull(message = "Price must be not null")
    private int price;

    @Column(nullable = false, unique = false)
    @NotBlank(message = "Description is mandatory")
    @NotNull(message = "Description must be not null")
    private String description;

    @Column(nullable = true, unique = false, length = 200)
    @NotBlank(message = "Status is mandatory")
    @NotNull(message = "Status must be not null")
    private String status;

    @Column(nullable = false, unique = false, length = 200)
    @NotBlank(message = "Status is mandatory")
    @NotNull(message = "Status must be not null")
    private String image;

    @Column(name = "category_id")
    private Long categoryId;


    @ManyToOne()
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "category_id",insertable = false, updatable = false)
    private Category category;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<OrderDetail> orderDetailList = new HashSet<>();

}
