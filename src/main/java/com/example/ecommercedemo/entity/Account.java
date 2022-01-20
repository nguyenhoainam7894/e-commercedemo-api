package com.example.ecommercedemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = false,length = 200)
    @NotBlank(message = "Username name is mandatory")
    private String username;

    @Column(nullable = false,unique = false,length = 200)
    @NotEmpty
    @Email
    private String email;

    @Column(nullable = false,unique = false)
    @NotEmpty
    @Size(min = 8, message = "password should have at least 8 characters")
    private String password;

    @OneToMany(mappedBy = "account")
    private List<Order>orders=new ArrayList<>();

}
