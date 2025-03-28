package com.viniciusdevassis.stock.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IntegerOnlyValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IntegerOnly {
    String message() default "O campo deve conter apenas números";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
