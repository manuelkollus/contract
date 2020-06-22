package io.github.manuelkollus.contract.method;

import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.github.manuelkollus.contract.Contract;
import io.github.manuelkollus.contract.Ignored;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

public final class MethodFactory {
  private final JoinPoint joinPoint;
  private final MethodSignature signature;

  private MethodFactory(
    JoinPoint joinPoint,
    MethodSignature signature
  ) {
    this.joinPoint = joinPoint;
    this.signature = signature;
  }

  public Method createMethod() {
    return Method.newBuilder()
      .withName(signature.getName())
      .withContract(resolveContractAnnotation())
      .withParameters(createMethodParameters())
      .create();
  }

  private static final int RANGE_START_INDEX = 0;

  private List<MethodParameter> createMethodParameters() {
    Object[] arguments = joinPoint.getArgs();
    return IntStream.range(RANGE_START_INDEX, arguments.length)
      .mapToObj(this::createParameter)
      .collect(Collectors.toList());
  }

  private static final Class<? extends Contract> CONTRACT_ANNOTATION = Contract.class;

  private Contract resolveContractAnnotation() {
    return signature.getMethod()
      .getDeclaredAnnotation(CONTRACT_ANNOTATION);
  }

  private MethodParameter createParameter(int index) {
    return MethodParameter.newBuilder()
      .withName(resolveParameterName(index))
      .withDeclaringType(resolveDeclaringType(index))
      .withIgnored(resolveIgnoredAnnotation(index))
      .withValue(resolveParameterValue(index))
      .create();
  }

  private String resolveParameterName(int index) {
    String[] names = signature.getParameterNames();
    return names[index];
  }

  private Class<?> resolveDeclaringType(int index) {
    Class<?>[] types = signature.getParameterTypes();
    return types[index];
  }

  private Object resolveParameterValue(int index) {
    Object[] arguments = joinPoint.getArgs();
    return arguments[index];
  }

  private static final Class<? extends Ignored> IGNORED_ANNOTATION = Ignored.class;

  private Ignored resolveIgnoredAnnotation(int index) {
    Parameter parameter = signature.getMethod()
      .getParameters()[index];
    return parameter.getDeclaredAnnotation(IGNORED_ANNOTATION);
  }

  public static MethodFactory create(
    JoinPoint joinPoint,
    MethodSignature signature
  ) {
    Objects.requireNonNull(joinPoint);
    Objects.requireNonNull(signature);
    return new MethodFactory(
      joinPoint, signature
    );
  }
}
