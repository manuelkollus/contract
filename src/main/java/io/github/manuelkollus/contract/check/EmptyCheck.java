package io.github.manuelkollus.contract.check;

import io.github.manuelkollus.contract.error.ErrorMessage;
import io.github.manuelkollus.contract.method.MethodParameter;

public final class EmptyCheck implements Check {
  public EmptyCheck() {}

  @Override
  public boolean validateParameter(MethodParameter parameter) {
    return false;
  }

  @Override
  public ErrorMessage createErrorMessage(MethodParameter parameter) {
    return null;
  }

  public static EmptyCheck create() {
    return new EmptyCheck();
  }
}
