package com.inventory.demo.service;

import com.inventory.demo.dto.ProductDto;
import com.inventory.demo.dto.Response;

public interface ProductService {
    Response saveProduct(ProductDto productDto);
}
