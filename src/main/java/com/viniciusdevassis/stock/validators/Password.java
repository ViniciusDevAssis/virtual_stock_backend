package com.viniciusdevassis.stock.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    String message() default "A senha deve ter pelo menos 8 caracteres, incluindo 1 letra maiúscula, 1 minúscula, 1 número e 1 caractere especial.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
