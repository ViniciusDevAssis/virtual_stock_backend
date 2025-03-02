package com.viniciusdevassis.stock.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Name {

    String message() default "O Nome de usuário deve conter apenas letras, não pode ser vazio ou conter números";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
