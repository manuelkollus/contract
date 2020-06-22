package io.github.manuelkollus.contract.check;

import io.github.manuelkollus.contract.error.ErrorMessage;
import io.github.manuelkollus.contract.method.MethodParameter;

@CheckType(
  types = {
    int.class,
    Integer.class,
    long.class,
    Long.class,
  }
)
public final class UnsignedCheck extends AbstractCheck {
  public UnsignedCheck() {}

  private static final int ZERO_NUMBER = 0;

  @Override
  public boolean validateParameter() {
    MethodParameter parameter = parameter();
    long value = (long) parameter.value();
    return value < ZERO_NUMBER;
  }

  private static final String DESCRIPTION_MESSAGE = "%desc%";

  @Override
  public ErrorMessage createErrorMessage() {
    MethodParameter parameter = parameter();
    return ErrorMessage.of(
      String.format("value %s cannot be negative", parameter.name()),
      DESCRIPTION_MESSAGE
    );
  }
}
