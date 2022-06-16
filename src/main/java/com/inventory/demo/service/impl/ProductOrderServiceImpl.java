package com.inventory.demo.service.impl;

import com.inventory.demo.dto.ProductOrderDto;
import com.inventory.demo.entity.ProductOrder;
import com.inventory.demo.repository.ProductOrderRepository;
import com.inventory.demo.service.ProductOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {

    private final ProductOrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public ProductOrderServiceImpl(ProductOrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveProductOrder(ProductOrderDto productOrderDto) {
        ProductOrder productOrder = modelMapper.map(productOrderDto, ProductOrder.class);
        productOrder.getProductOrderDetails().forEach(productOrderDetails -> productOrderDetails.setProductOrder(productOrder));
        orderRepository.save(productOrder);
    }
}
