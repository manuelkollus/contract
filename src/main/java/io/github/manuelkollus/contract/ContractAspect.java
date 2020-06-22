package io.github.manuelkollus.contract;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ContractAspect {
  @Around("execution(* *(..)) && @annotation(Contract)")
  public Object proceedContract(ProceedingJoinPoint joinPoint) throws Throwable {
    Precondition precondition = Precondition.create(joinPoint);
    precondition.proceedCheckMethod();
    return joinPoint.proceed();
  }
}