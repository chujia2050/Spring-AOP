package com.example.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class MyCloudLogAsyncAspect {

    @Before("com.example.aop.aspect.AopExpressions.forDaoPackageNoGetterSetter())")
    public void logToCloudAdvice() {
        System.out.println("\n=======>>> Logging to Cloud in async fashion");
    }
}
