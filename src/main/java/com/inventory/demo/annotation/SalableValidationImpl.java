package com.inventory.demo.annotation;

import com.inventory.demo.dto.ProductDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SalableValidationImpl implements ConstraintValidator<SalableValidation, ProductDto> {
    @Override
    public void initialize(SalableValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /*TODO
     *  Need to find a proper solution for this custom validation
     *  This implementation works fine for one field but need to achieve a way for multiple fields*/
    @Override
    public boolean isValid(ProductDto productDto, ConstraintValidatorContext constraintValidatorContext) {
        return productDto.getSalablePrice() > productDto.getOriginalPrice();
    }
}
