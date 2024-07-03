package org.example.sbccmsapi.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsTodayOrAfterValidator.class)
public @interface IsTodayOrAfter {
  String message() default "Data deve ser maior ou igual a data de hoje";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
