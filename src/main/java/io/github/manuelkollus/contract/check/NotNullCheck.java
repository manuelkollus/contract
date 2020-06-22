package io.github.manuelkollus.contract.check;

import io.github.manuelkollus.contract.error.ErrorMessage;
import io.github.manuelkollus.contract.method.MethodParameter;

@CheckType(
  types = {
    String.class,
    Object.class
  }
)
public final class NotNullCheck extends AbstractCheck {
  @Override
  public boolean validateParameter() {
    MethodParameter parameter = parameter();
    Object value = parameter.value();
    return value == null;
  }

  private static final String DESCRIPTION_MESSAGE = "%desc%";

  @Override
  public ErrorMessage createErrorMessage() {
    MethodParameter parameter = parameter();
    return ErrorMessage.of(
      String.format("value %s cannot be null", parameter.name()),
      DESCRIPTION_MESSAGE
    );
  }
}