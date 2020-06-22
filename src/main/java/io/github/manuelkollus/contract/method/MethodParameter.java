package io.github.manuelkollus.contract.method;

import io.github.manuelkollus.contract.Ignored;

public final class MethodParameter {
  private String name;
  private Class<?> declaringType;
  private Ignored ignored;
  private Object value;

  private MethodParameter(
    String name,
    Class<?> declaringType,
    Ignored ignored,
    Object value
  ) {
    this.name = name;
    this.declaringType = declaringType;
    this.ignored = ignored;
    this.value = value;
  }

  public String name() {
    return name;
  }

  public Class<?> declaringType() {
    return declaringType;
  }

  public boolean hasNotIgnoredAnnotation() {
    return ignored == null;
  }

  public Object value() {
    return value;
  }

  public static Builder newBuilder() {
    MethodParameter prototype = new MethodParameter(
      "",
      null,
      null,
      null
    );
    return newBuilder(prototype);
  }

  public static Builder newBuilder(MethodParameter prototype) {
    return new Builder(prototype);
  }

  public static final class Builder {
    private final MethodParameter prototype;

    private Builder(MethodParameter prototype) {
      this.prototype = prototype;
    }

    public Builder withName(String name) {
      this.prototype.name = name;
      return this;
    }

    public Builder withDeclaringType(Class<?> declaringType) {
      this.prototype.declaringType = declaringType;
      return this;
    }

    public Builder withIgnored(Ignored ignored) {
      this.prototype.ignored = ignored;
      return this;
    }

    public Builder withValue(Object value) {
      this.prototype.value = value;
      return this;
    }

    public MethodParameter create() {
      return new MethodParameter(
        prototype.name,
        prototype.declaringType,
        prototype.ignored,
        prototype.value
      );
    }
  }
}
