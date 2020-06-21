package io.github.manuelkollus.contract;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.github.manuelkollus.contract.check.Check;
import io.github.manuelkollus.contract.check.CheckRepository;
import io.github.manuelkollus.contract.check.EmptyCheck;
import io.github.manuelkollus.contract.error.ErrorMessage;
import io.github.manuelkollus.contract.method.Method;
import io.github.manuelkollus.contract.method.MethodFactory;
import io.github.manuelkollus.contract.method.MethodParameter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

public final class Precondition {
  private final JoinPoint joinPoint;
  private final CheckRepository checkRepository;

  private Precondition(JoinPoint joinPoint) {
    this.joinPoint = joinPoint;
    this.checkRepository = CheckRepository.create();
  }

  public void proceedCheckMethod() {
    Method method = createMethod();
    List<ContractFailure> failures = findContractFailures(method);
    failures.forEach(this::reportContractFailure);
  }

  private MethodSignature resolveMethodSignature() {
    return (MethodSignature) joinPoint.getSignature();
  }

  private Method createMethod() {
    MethodSignature signature = resolveMethodSignature();
    return MethodFactory.create(joinPoint, signature)
      .createMethod();
  }

  private List<ContractFailure> findContractFailures(Method method) {
    List<ContractFailure> failures = new ArrayList<>();
    for ( MethodParameter parameter : method.parameters() ) {
      Check check = findCheckForParameter(parameter);
      if (check.validateParameter(parameter)) {
        ContractFailure failure = ContractFailure.newBuilder()
          .withMethod(method)
          .withParameter(parameter)
          .withCheck(check)
          .create();
        failures.add(failure);
      }
    }
    return failures;
  }

  private static final Check EMPTY_CHECK = EmptyCheck.create();

  private Check findCheckForParameter(MethodParameter parameter) {
    return checkRepository.checks()
      .stream()
      .map(checkClass -> checkRepository.find(checkClass, parameter.declaringType()))
      .filter(Objects::nonNull)
      .findFirst()
      .orElse(EMPTY_CHECK);
  }

  private void reportContractFailure(ContractFailure failure) {
    Contract contract = failure.method()
      .contract();
    MethodParameter parameter = failure.parameter();
    Check check = failure.check();
    ErrorMessage errorMessage = check.createErrorMessage(parameter);
    errorMessage.throwException(contract.error());
  }

  public static Precondition create(JoinPoint joinPoint) {
    Objects.requireNonNull(joinPoint);
    return new Precondition(joinPoint);
  }
}
