package io.github.manuelkollus.contract.check;

import io.github.manuelkollus.contract.error.ErrorMessage;
import io.github.manuelkollus.contract.method.MethodParameter;

public interface Check {
  boolean validateParameter(MethodParameter parameter);

  ErrorMessage createErrorMessage(MethodParameter parameter);
}