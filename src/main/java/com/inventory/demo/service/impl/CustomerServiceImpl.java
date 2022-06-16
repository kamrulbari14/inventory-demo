package com.inventory.demo.service.impl;

import com.inventory.demo.dto.CustomerDto;
import com.inventory.demo.entity.Customer;
import com.inventory.demo.enums.ActiveStatus;
import com.inventory.demo.repository.CustomerRepository;
import com.inventory.demo.service.CustomerService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean saveCustomer(CustomerDto customerDto) {
        Optional<Customer> existingCustomer = customerRepository.findByEmailAndIsActive(customerDto.getEmail(),
                ActiveStatus.ACTIVE.getValue());
        Customer customer;
        if (existingCustomer.isPresent()) {
            return false;
        }
        customerDto.setIsActive(ActiveStatus.ACTIVE.getValue());
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        customer = modelMapper.map(customerDto, Customer.class);
        customerRepository.save(customer);
        return true;
    }
}
