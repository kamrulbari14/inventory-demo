package com.inventory.demo.service;

import com.inventory.demo.dto.ProductDto;
import com.inventory.demo.dto.Response;

import java.util.List;

public interface ProductService {
    void saveProduct(ProductDto productDto);

    Response updateProduct(ProductDto productDto);

    ProductDto getProductById(Long productId);

    ProductDto getProductByName(String productName);

    List<ProductDto> getPaginationAndSortedData(int pageNo, int pageSize, String sortedBy, String sortedDirection);
}
