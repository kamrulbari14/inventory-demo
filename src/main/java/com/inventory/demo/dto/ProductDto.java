package com.inventory.demo.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto extends BaseDto{
    private String productName;
    private String batchId;
    private Double originalPrice;
    private Double salablePrice;
    private Double totalQuantity;
}
