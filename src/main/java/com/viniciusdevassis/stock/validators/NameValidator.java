package com.viniciusdevassis.stock.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<Name, String> {

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if (name == null || name.trim().isEmpty()) {
            return true;
        }
        return name.matches("^[a-zA-ZáàâãäåçéèêíïóôõöúüñA-ZÁÀÂÃÄÅÇÉÈÊÍÏÓÔÕÖÚÜÑ ]+$");
    }
}
