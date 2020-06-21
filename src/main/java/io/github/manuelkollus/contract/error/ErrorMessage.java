package io.github.manuelkollus.contract.error;

import java.util.Objects;

import io.github.manuelkollus.contract.ContractException;

public final class ErrorMessage {
  private final String message;
  private final String description;

  private static final String EMPTY_STRING = "";

  private ErrorMessage(String message) {
    this.message = message;
    this.description = EMPTY_STRING;
  }

  private ErrorMessage(String message, String description) {
    this.message = message;
    this.description = description;
  }

  public String message(ErrorModel errorModel) {
    if (errorModel == ErrorModel.STANDARD) {
      return String.format("%s, %s", message, description);
    }
    return String.format("%s", message);
  }

  public void throwException(ErrorModel errorModel) {
    try {
      String message = message(errorModel);
      throw ContractException.withMessage(message);
    } catch (ContractException contractFailure) {
      contractFailure.printStackTrace();
    }
  }

  public static ErrorMessage of(String message) {
    Objects.requireNonNull(message);
    return new ErrorMessage(message);
  }

  public static ErrorMessage of(String message, String description) {
    Objects.requireNonNull(message);
    Objects.requireNonNull(description);
    return new ErrorMessage(message, description);
  }
}
