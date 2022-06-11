package com.inventory.demo.dto;

import com.inventory.demo.annotation.SalableValidation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SalableValidation
public class ProductDto extends BaseDto {
    @NotBlank(message = "Product name is mandatory and can not be empty")
    private String productName;
    @Min(value = 0, message = "Product original price can not be less than zero")
    private Double originalPrice;
    @Min(value = 0, message = "Product saleable price can not be less than zero")
    private Double salablePrice;
    @Min(value = 1, message = "Product quantity can not be zero or less than zero")
    private Long totalQuantity;
}
