package io.github.manuelkollus.contract.check;

import io.github.manuelkollus.contract.error.ErrorMessage;

public final class EmptyCheck extends AbstractCheck {
  @Override
  public boolean isInvalid() {
    return false;
  }

  @Override
  public ErrorMessage createErrorMessage() {
    return null;
  }

  public static EmptyCheck create() {
    return new EmptyCheck();
  }
}
