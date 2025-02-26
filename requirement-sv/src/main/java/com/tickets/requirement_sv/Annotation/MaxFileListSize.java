package com.tickets.requirement_sv.Annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MaxFileListValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxFileListSize {

    String message() default "Max file list size is 5";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int max() default 5;
}
