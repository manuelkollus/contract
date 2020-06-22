package io.github.manuelkollus.contract;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.github.manuelkollus.contract.check.AbstractCheck;
import io.github.manuelkollus.contract.error.ErrorModel;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Contract {
  ErrorModel error() default ErrorModel.STANDARD;
  Class<? extends AbstractCheck>[] checks();
}
