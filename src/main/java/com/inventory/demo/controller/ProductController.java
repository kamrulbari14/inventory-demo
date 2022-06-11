package com.inventory.demo.controller;

import com.inventory.demo.annotation.ApiController;
import com.inventory.demo.dto.ProductDto;
import com.inventory.demo.dto.Response;
import com.inventory.demo.service.ProductService;
import com.inventory.demo.util.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
        productService.saveProduct(productDto);
        return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, "Product Created Successfully!"
                , null);
    }

    @PatchMapping("/update-product")
    public Response updateProduct(@Valid @RequestBody ProductDto productDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseBuilder.getFailureResponse(result, "");
        }
        return productService.updateProduct(productDto);
    }

    @GetMapping("/get-product-by-id")
    public Response getProductById(@RequestParam("product_id") Long productId) {
        ProductDto productDto = productService.getProductById(productId);
        if (productDto != null) {
            return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Product Retrieved Successfully!"
                    , productDto);
        }
        return ResponseBuilder.getSuccessResponse(HttpStatus.NOT_FOUND, "Product Not Found!"
                , null);
    }

    @GetMapping("/get-product-by-name")
    public Response getProductByName(@RequestParam("product_name") String productName) {
        ProductDto productDto = productService.getProductByName(productName);
        if (productDto != null) {
            return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Product Retrieved Successfully!"
                    , productDto);
        }
        return ResponseBuilder.getSuccessResponse(HttpStatus.NOT_FOUND, "Product Not Found!"
                , null);
    }

    @GetMapping("/get-product-list")
    public Response getPaginationAndSortedData(@RequestParam(defaultValue = "0") int pageNo,
                                               @RequestParam(defaultValue = "10") int pageSize,
                                               @RequestParam(defaultValue = "id") String sortedBy,
                                               @RequestParam(defaultValue = "DESC") String sortedDirection) {
        List<ProductDto> productList = productService.getPaginationAndSortedData(pageNo, pageSize, sortedBy,
                sortedDirection);
        if (!productList.isEmpty()) {
            return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Product Retrieved Successfully!"
                    , productList);
        }
        return ResponseBuilder.getSuccessResponse(HttpStatus.NOT_FOUND, "No Product Found!"
                , null);
    }

}
