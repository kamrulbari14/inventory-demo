package com.inventory.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Setter
@Getter
@Entity
public class Product extends BaseEntity {
    private String productName;
    private Double originalPrice;
    private Double sellablePrice;
    private Double totalQuantity;

}
