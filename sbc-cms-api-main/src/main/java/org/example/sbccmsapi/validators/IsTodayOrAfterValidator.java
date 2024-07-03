package org.example.sbccmsapi.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class IsTodayOrAfterValidator implements ConstraintValidator<IsTodayOrAfter, LocalDate> {
  @Override
  public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
    return !date.isBefore(LocalDate.now());
  }
}
