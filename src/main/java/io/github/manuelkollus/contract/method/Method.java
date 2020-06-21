package io.github.manuelkollus.contract.method;

import java.util.ArrayList;
import java.util.List;

import io.github.manuelkollus.contract.Contract;

public final class Method {
  private String name;
  private Contract contract;
  private List<MethodParameter> parameters;

  private Method(
    String name,
    Contract contract,
    List<MethodParameter> parameters
  ) {
    this.name = name;
    this.contract = contract;
    this.parameters = parameters;
  }

  public String name() {
    return name;
  }

  public Contract contract() {
    return contract;
  }

  public List<MethodParameter> parameters() {
    return parameters;
  }

  public static Builder newBuilder() {
    Method prototype = new Method(
      "",
      null,
      new ArrayList<>()
    );
    return newBuilder(prototype);
  }

  public static Builder newBuilder(Method prototype) {
    return new Builder(prototype);
  }

  public static final class Builder {
    private final Method prototype;

    private Builder(Method prototype) {
      this.prototype = prototype;
    }

    public Builder withName(String name) {
      this.prototype.name = name;
      return this;
    }

    public Builder withContract(Contract contract) {
      this.prototype.contract = contract;
      return this;
    }

    public Builder withParameters(List<MethodParameter> parameters) {
      this.prototype.parameters = parameters;
      return this;
    }

    public Method create() {
      return new Method(
        prototype.name,
        prototype.contract,
        prototype.parameters
      );
    }
  }
}
