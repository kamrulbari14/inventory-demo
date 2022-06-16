package com.inventory.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto extends BaseDto{
    @NotBlank(message = "Customer name is mandatory and can not be empty")
    private String name;
    @Email(message = "A valid email address required")
    private String email;
    private String address;
}
