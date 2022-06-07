package com.inventory.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Setter
@Getter
@Entity
public class Product extends BaseEntity {
    private String productName;
    private String batchId;
    private Double originalPrice;
    private Double salablePrice;
    private Double totalQuantity;
}
