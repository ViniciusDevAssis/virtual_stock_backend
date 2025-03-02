package com.viniciusdevassis.stock.validators;

import com.viniciusdevassis.stock.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailAvailableValidator implements ConstraintValidator<EmailAvailable, String> {

    @Autowired
    private UserRepository repository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !repository.existsByEmail(email);
    }
}
