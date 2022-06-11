package com.inventory.demo.service.impl;

import com.inventory.demo.dto.ProductDto;
import com.inventory.demo.dto.Response;
import com.inventory.demo.entity.Product;
import com.inventory.demo.enums.ActiveStatus;
import com.inventory.demo.repository.ProductRepository;
import com.inventory.demo.service.ProductService;
import com.inventory.demo.util.ResponseBuilder;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveProduct(ProductDto productDto) {
/*        if (!productDto.getSalablePrice() > productDto.getOriginalPrice()) {
            return ResponseBuilder.getFailureResponse(HttpStatus.BAD_REQUEST, "Salable Price Should greater than" +
                    " Original Price");
        }*/
        Optional<Product> existingProduct = productRepository.findByProductNameAndIsActive(productDto.getProductName(),
                ActiveStatus.ACTIVE.getValue());
        Product product;
        existingProduct.ifPresent(value -> productDto.setId(value.getId()));
        productDto.setIsActive(ActiveStatus.ACTIVE.getValue());
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        product = modelMapper.map(productDto, Product.class);
        productRepository.save(product);
    }

    @Override
    public Response updateProduct(ProductDto productDto) {
        Optional<Product> existingProduct = productRepository.findByProductNameAndIsActive(productDto.getProductName(),
                ActiveStatus.ACTIVE.getValue());
        Product product;
        if (existingProduct.isPresent()) {
            return ResponseBuilder.getFailureResponse(HttpStatus.BAD_REQUEST, "Product name already exists");
        }
        existingProduct = productRepository.findByIdAndIsActive(productDto.getId(),
                ActiveStatus.ACTIVE.getValue());
        if (existingProduct.isEmpty()) {
            return ResponseBuilder.getFailureResponse(HttpStatus.BAD_REQUEST, "Product can't be found");
        }
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        product = modelMapper.map(productDto, Product.class);
        productRepository.save(product);
        return ResponseBuilder.getSuccessResponse(HttpStatus.ACCEPTED, "Product Updated Successfully!"
                , null);
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Optional<Product> existingProduct = productRepository.findByIdAndIsActive(productId,
                ActiveStatus.ACTIVE.getValue());
        return existingProduct.map(product -> modelMapper.map(product, ProductDto.class)).orElse(null);
    }

    @Override
    public ProductDto getProductByName(String productName) {
        Optional<Product> existingProduct = productRepository.findByProductNameAndIsActive(productName,
                ActiveStatus.ACTIVE.getValue());
        return existingProduct.map(product -> modelMapper.map(product, ProductDto.class)).orElse(null);
    }

    @Override
    public List<ProductDto> getPaginationAndSortedData(int pageNo, int pageSize, String sortedBy, String sortedDirection) {
        String sortDir = !sortedDirection.isBlank() ? Sort.Direction.ASC.name() : Sort.Direction.DESC.name();
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortedBy).ascending()
                : Sort.by(sortedBy).descending();
        Pageable page = PageRequest.of(pageNo, pageSize, sort);
        Page<Product> pageValue = productRepository.findAll(page);
        if (pageValue.hasContent()) {
            List<ProductDto> productDtoList = new ArrayList<>();
            pageValue.getContent().forEach(product -> {
                modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
                ProductDto productDto = modelMapper.map(product, ProductDto.class);
                productDtoList.add(productDto);
            });
            return productDtoList;
        }
        return null;
    }
}
