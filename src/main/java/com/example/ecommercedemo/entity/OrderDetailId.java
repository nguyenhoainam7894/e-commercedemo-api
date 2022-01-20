package com.example.ecommercedemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderDetailId implements Serializable {
    @Column(name = "order_id")
    private long orderId;
    @Column(name = "product_id")
    private long productId;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetailId)) return false;
        OrderDetailId saleId = (OrderDetailId) o;
        return Objects.equals(getOrderId(), saleId.getOrderId()) &&
                Objects.equals(getProductId(), saleId.getProductId());
    }
    @Override
    public int hashCode() {

        return Objects.hash(getOrderId(), getProductId());
    }
}
