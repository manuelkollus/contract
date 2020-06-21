package io.github.manuelkollus.contract.check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class CheckRepository {
  private final Map<Class<?>, Check> checks;

  private CheckRepository(Map<Class<?>, Check> checks) {
    this.checks = checks;
  }

  public Check find(Class<?> checkClass, Class<?> declaringClass) {
    return checks.entrySet()
      .stream()
      .filter(entry -> entry.getKey().equals(checkClass))
      .filter(classCheckEntry -> isEntryValid(classCheckEntry, declaringClass))
      .map(Map.Entry::getValue)
      .findFirst()
      .orElse(null);
  }

  private boolean isEntryValid(
    Map.Entry<Class<?>, Check> entry,
    Class<?> declaringClass
  ) {
    Class<?> checkClass = entry.getKey();
    CheckType checkType = findCheckTypeAnnotation(checkClass);
    return isTypePresent(checkType, declaringClass);
  }

  private boolean isTypePresent(CheckType checkType, Class<?> declaringClass) {
    return Arrays.stream(checkType.types())
      .anyMatch(declaringClass::isAssignableFrom);
  }

  private static final Class<? extends CheckType> CHECK_TYPE_ANNOTATION = CheckType.class;

  private CheckType findCheckTypeAnnotation(Class<?> checkClass) {
    return checkClass.getDeclaredAnnotation(CHECK_TYPE_ANNOTATION);
  }

  public List<Class<?>> checks() {
    return new ArrayList<>(checks.keySet());
  }

  public static CheckRepository create() {
    Map<Class<?>, Check> checks = new HashMap<>();
    checks.put(NotNullCheck.class, new NotNullCheck());
    checks.put(UnsignedCheck.class, new UnsignedCheck());
    return create(checks);
  }

  public static CheckRepository create(Map<Class<?>, Check> checks) {
    Objects.requireNonNull(checks);
    return new CheckRepository(checks);
  }
}
