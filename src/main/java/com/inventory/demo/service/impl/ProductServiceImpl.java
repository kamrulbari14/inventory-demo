package com.inventory.demo.service.impl;

import com.inventory.demo.dto.ProductDto;
import com.inventory.demo.dto.Response;
import com.inventory.demo.entity.Product;
import com.inventory.demo.repository.ProductRepository;
import com.inventory.demo.service.ProductService;
import com.inventory.demo.util.ResponseBuilder;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Response saveProduct(ProductDto productDto) {
        Product product = productRepository.findByBatchIdAndIsActive(productDto.getBatchId(), Boolean.TRUE);
        if (product != null) {
            productDto.setId(product.getId());
        }
        productDto.setIsActive(Boolean.TRUE);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        product = modelMapper.map(productDto, Product.class);
        productRepository.save(product);
        return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, "Product Created Successfully!", null);
    }
}
