package com.inventory.demo.controller;

import com.inventory.demo.annotation.ApiController;
import com.inventory.demo.dto.ProductDto;
import com.inventory.demo.dto.Response;
import com.inventory.demo.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@ApiController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save-product")
    public Response saveProduct(@RequestBody ProductDto productDto) {
        return productService.saveProduct(productDto);
    }
}
