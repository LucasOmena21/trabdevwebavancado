package org.example.sbccmsapi.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidTimeRangeValidator.class)
public @interface ValidTimeRange {
  String message() default "Horário inicial deve ser menor que o horário final";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
