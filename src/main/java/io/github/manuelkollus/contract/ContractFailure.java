package io.github.manuelkollus.contract;

import java.util.Objects;

import io.github.manuelkollus.contract.check.Check;
import io.github.manuelkollus.contract.method.Method;
import io.github.manuelkollus.contract.method.MethodParameter;

public final class ContractFailure {
  private final Method method;
  private final MethodParameter methodParameter;
  private final Check check;

  private ContractFailure(
    Method method,
    MethodParameter methodParameter,
    Check check
  ) {
    this.method = method;
    this.methodParameter = methodParameter;
    this.check = check;
  }

  public Method method() {
    return method;
  }

  public MethodParameter methodParameter() {
    return methodParameter;
  }

  public Check check() {
    return check;
  }

  public static final class Builder {

  }
  public static ContractFailure create(
    Method method,
    MethodParameter methodParameter,
    Check check
  ) {
    Objects.requireNonNull(method);
    Objects.requireNonNull(methodParameter);
    Objects.requireNonNull(check);
    return new ContractFailure(
      method,
      methodParameter,
      check
    );
  }
}
