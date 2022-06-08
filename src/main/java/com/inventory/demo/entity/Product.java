package com.inventory.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Setter
@Getter
@Entity
public class Product extends BaseEntity {
    @Column(updatable = false, nullable = false)
    private String productName;
    @Column(columnDefinition = "INT UNSIGNED NOT NULL")
    private Double originalPrice;
    @Column(columnDefinition = "INT UNSIGNED NOT NULL")
    private Double salablePrice;
    @Column(columnDefinition = "INT UNSIGNED NOT NULL")
    private Double totalQuantity;
}
