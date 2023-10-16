package com.example.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
public class AopExpressions {


    @Pointcut("execution(* com.example.aop.dao.*.*(..))")
    public void forDaoPackage() {
    }

    @Pointcut("execution(* com.example.aop.dao.*.get*(..))")
    public void getter() {
    }

    @Pointcut("execution(* com.example.aop.dao.*.set*(..))")
    public void setter() {
    }

    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter() {
    }
}
