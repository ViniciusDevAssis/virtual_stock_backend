package com.viniciusdevassis.stock.validators;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IntegerOnlyValidator implements ConstraintValidator<IntegerOnly, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value == null || value instanceof Integer;
    }

}
