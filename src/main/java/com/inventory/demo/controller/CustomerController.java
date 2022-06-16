package com.inventory.demo.controller;

import com.inventory.demo.annotation.ApiController;
import com.inventory.demo.dto.CustomerDto;
import com.inventory.demo.dto.Response;
import com.inventory.demo.service.CustomerService;
import com.inventory.demo.util.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@ApiController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save-customer")
    public Response saveCustomer(@Valid @RequestBody CustomerDto customerDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseBuilder.getFailureResponse(result, "");
        }
        if (!customerService.saveCustomer(customerDto)) {
            return ResponseBuilder.getFailureResponse(HttpStatus.BAD_REQUEST, "There exist a customer with this email");
        }
        return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, "Customer Created Successfully!"
                , null);
    }
}
