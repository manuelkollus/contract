package io.github.manuelkollus.contract.check;

import io.github.manuelkollus.contract.error.ErrorMessage;
import io.github.manuelkollus.contract.method.MethodParameter;

public abstract class AbstractCheck {
  private MethodParameter parameter;

  public AbstractCheck() {
    this.parameter = MethodParameter.newBuilder().create();
  }

  public abstract boolean validateParameter();

  public abstract ErrorMessage createErrorMessage();

  public void parameter(MethodParameter parameter) {
    this.parameter = parameter;
  }

  public MethodParameter parameter() {
    return parameter;
  }
}