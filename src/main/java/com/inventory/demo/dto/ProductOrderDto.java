package com.inventory.demo.dto;

import com.inventory.demo.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrderDto extends BaseDto{

    @NotNull(message = "Order must be placed for a customer")
    private Customer customer;
    private Double totalAmount;
    @NotNull(message = "Order should have order details")
    private List<ProductOrderDetailsDto> productOrderDetails;
}
