package com.inventory.demo.dto;

import com.inventory.demo.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrderDetailsDto extends BaseDto{

    private Double unitPrice;
    private int quantity;
    private Double totalAmount;
    private Product product;
    private ProductOrderDto productOrder;
}
