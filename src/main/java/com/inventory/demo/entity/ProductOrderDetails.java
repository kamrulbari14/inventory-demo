package com.inventory.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Setter
@Getter
@Entity
public class ProductOrderDetails extends BaseEntity {

    private Double unitPrice;
    private int quantity;
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "product_order_id")
    private ProductOrder productOrder;
}
