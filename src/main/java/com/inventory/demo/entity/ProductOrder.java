package com.inventory.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
public class ProductOrder extends BaseEntity {

    @OneToOne
    private Customer customer;

    private Double totalAmount;

    @OneToMany(mappedBy = "productOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductOrderDetails> productOrderDetails;

}
