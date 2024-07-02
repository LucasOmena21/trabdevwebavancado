package org.example.sbccmsapi.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.sbccmsapi.dtos.requests.CriarAtividadeRequest;

public class ValidTimeRangeValidator implements ConstraintValidator<ValidTimeRange, CriarAtividadeRequest> {
  @Override
  public boolean isValid(CriarAtividadeRequest request, ConstraintValidatorContext context) {
    return request.horarioInicial().isBefore(request.horarioFinal());
  }
}
