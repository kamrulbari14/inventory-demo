package com.inventory.demo.controller;

import com.inventory.demo.annotation.ApiController;
import com.inventory.demo.dto.ProductOrderDto;
import com.inventory.demo.dto.Response;
import com.inventory.demo.service.ProductOrderService;
import com.inventory.demo.util.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@ApiController
public class ProductOrderController {

    private final ProductOrderService orderService;

    public ProductOrderController(ProductOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/save-product-order")
    public Response saveOrder(@Valid @RequestBody ProductOrderDto productOrderDto, BindingResult result){
        if (result.hasErrors()) {
            return ResponseBuilder.getFailureResponse(result, "");
        }
        orderService.saveProductOrder(productOrderDto);
        return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, "Order placed Successfully!"
                , null);
    }
}
