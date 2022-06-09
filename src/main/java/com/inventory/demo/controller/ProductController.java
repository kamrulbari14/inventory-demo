package com.inventory.demo.controller;

import com.inventory.demo.annotation.ApiController;
import com.inventory.demo.dto.ProductDto;
import com.inventory.demo.dto.Response;
import com.inventory.demo.service.ProductService;
import com.inventory.demo.util.ResponseBuilder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@ApiController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save-product")
    public Response saveProduct(@Valid @RequestBody ProductDto productDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseBuilder.getFailureResponse(result, "");
        }
        return productService.saveProduct(productDto);
    }
}
