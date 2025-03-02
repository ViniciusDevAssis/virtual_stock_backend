package com.viniciusdevassis.stock.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        boolean hasValidLength = password.length() >= 8;
        boolean hasUpperCase = password.chars().anyMatch(Character::isUpperCase);
        boolean hasLowerCase = password.chars().anyMatch(Character::isLowerCase);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);
        boolean hasSpecialChar = password.chars().anyMatch(c -> "!@#$%^&*()-_=+{}[]|:;\"'<>,.?/".indexOf(c) >= 0);
        return hasValidLength && hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar;
    }
}
