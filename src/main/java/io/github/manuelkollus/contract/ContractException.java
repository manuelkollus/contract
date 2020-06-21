package io.github.manuelkollus.contract;

import java.util.Objects;

public final class ContractException extends Exception{
  private ContractException(String message) {
    super(message);
  }

  public static ContractException withMessage(String message) {
    Objects.requireNonNull(message);
    return new ContractException(message);
  }
}
