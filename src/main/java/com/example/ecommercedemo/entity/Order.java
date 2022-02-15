package com.example.ecommercedemo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
    private LocalDate createdat = LocalDate.now();

    @Column(nullable = false, unique = false)
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
    private LocalDate updateat = LocalDate.now();

    @Column(nullable = false, unique = false, length = 200)
    private String status;

    @Column(nullable = false, unique = false, length = 200)
    private String shipname;

    @Column(nullable = false, unique = false, length = 200)
    private String shipaddress;

    @Column(nullable = false, unique = false, length = 200)
    private Long shipphone;

    @Column(name = "account_id")
    private Long accountId;

    private int totalPrice;


    @ManyToOne()
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "account_id",insertable = false, updatable = false)
    private Account account;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetailList = new HashSet<>();

    public void addOrderDetail(OrderDetail orderDetail){
        this.orderDetailList.add(orderDetail);
    }
}
