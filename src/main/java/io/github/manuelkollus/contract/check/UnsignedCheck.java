package io.github.manuelkollus.contract.check;

import io.github.manuelkollus.contract.error.ErrorMessage;
import io.github.manuelkollus.contract.method.MethodParameter;

@CheckType(
  types = {
    Float.class,
    Integer.class,
    int.class,
    Long.class,
  }
)
public final class UnsignedCheck implements Check {
  public UnsignedCheck() {}

  private static final int ZERO_NUMBER = 0;

  @Override
  public boolean validateParameter(
    MethodParameter parameter
  ) {
    int value = (int) parameter.value();
    return value < ZERO_NUMBER;
  }

  private static final String DESCRIPTION_MESSAGE = "%desc%";

  @Override
  public ErrorMessage createErrorMessage(
    MethodParameter parameter
  ) {
    return ErrorMessage.of(
      String.format("value %s cannot be negative", parameter.name()),
      DESCRIPTION_MESSAGE
    );
  }
}
