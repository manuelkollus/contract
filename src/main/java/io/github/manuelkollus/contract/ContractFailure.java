package io.github.manuelkollus.contract;

import io.github.manuelkollus.contract.check.Check;
import io.github.manuelkollus.contract.check.EmptyCheck;
import io.github.manuelkollus.contract.method.Method;
import io.github.manuelkollus.contract.method.MethodParameter;

public final class ContractFailure {
  private Method method;
  private MethodParameter parameter;
  private Check check;

  private ContractFailure(
    Method method,
    MethodParameter parameter,
    Check check
  ) {
    this.method = method;
    this.parameter = parameter;
    this.check = check;
  }

  public Method method() {
    return method;
  }

  public MethodParameter parameter() {
    return parameter;
  }

  public Check check() {
    return check;
  }

  public static Builder newBuilder() {
    ContractFailure prototype = new ContractFailure(
      Method.newBuilder().create(),
      MethodParameter.newBuilder().create(),
      EmptyCheck.create()
    );
    return newBuilder(prototype);
  }

  public static Builder newBuilder(ContractFailure prototype) {
    return new Builder(prototype);
  }

  public static final class Builder {
    private final ContractFailure prototype;

    private Builder(ContractFailure prototype) {
      this.prototype = prototype;
    }

    public Builder withMethod(Method method) {
      this.prototype.method = method;
      return this;
    }

    public Builder withParameter(MethodParameter parameter) {
      this.prototype.parameter = parameter;
      return this;
    }

    public Builder withCheck(Check check) {
      this.prototype.check = check;
      return this;
    }

    public ContractFailure create() {
      return new ContractFailure(
        prototype.method,
        prototype.parameter,
        prototype.check
      );
    }
  }
}
