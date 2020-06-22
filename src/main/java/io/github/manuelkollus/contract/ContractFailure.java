package io.github.manuelkollus.contract;

import io.github.manuelkollus.contract.check.AbstractCheck;
import io.github.manuelkollus.contract.check.EmptyCheck;
import io.github.manuelkollus.contract.method.Method;
import io.github.manuelkollus.contract.method.MethodParameter;

public final class ContractFailure {
  private Method method;
  private AbstractCheck check;

  private ContractFailure(
    Method method,
    AbstractCheck check
  ) {
    this.method = method;
    this.check = check;
  }

  public Method method() {
    return method;
  }

  public AbstractCheck check() {
    return check;
  }

  public static Builder newBuilder() {
    ContractFailure prototype = new ContractFailure(
      Method.newBuilder().create(),
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

    public Builder withCheck(AbstractCheck check) {
      this.prototype.check = check;
      return this;
    }

    public ContractFailure create() {
      return new ContractFailure(
        prototype.method,
        prototype.check
      );
    }
  }
}
