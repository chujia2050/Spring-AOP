package com.example.aop.aspect;

import com.example.aop.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyLoggingAspect {

    @Around("execution(* com.example.aop.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n=======>>> Executing @Around advice on method: " + method);

        long begin = System.currentTimeMillis();
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            System.out.println("@Around advice: We have a problem " + e);
            //result = "Nothing exciting here. Move along!";
            throw e;
        }
        long end = System.currentTimeMillis();
        long duration = end - begin;
        System.out.println("\n=======>>> Duration: " + duration + " milliseconds");
        return result;
    }

    @After("execution(* com.example.aop.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=======>>> Executing @After(finally) advice on method: " + method);
    }

    @AfterThrowing(pointcut = "execution(* com.example.aop.dao.AccountDAO.findAccounts(..))", throwing = "exception")
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable exception) {

        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=======>>> Executing @AfterThrowing advice on method: " + method);
        System.out.println("\n=======>>> The exception is: " + exception);
    }

    @AfterReturning(pointcut = "execution(* com.example.aop.dao.AccountDAO.findAccounts(..))", returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {

        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=======>>> Executing @AfterReturning advice on method: " + method);

        System.out.println("\n=======>>> result is: " + result);

        convertAccountNamestoUpperCase(result);
        System.out.println("\n=======>>> result is: " + result);
//        if (!result.isEmpty()) {
//            Account tempAccount = result.get(0);
//            tempAccount.setName("Daffy Duck");
//        }
    }

    private void convertAccountNamestoUpperCase(List<Account> result) {

        for (Account tempAccount : result) {

            String upperName = tempAccount.getName().toUpperCase();
            tempAccount.setName(upperName);
        }
    }

    @Before("com.example.aop.aspect.AopExpressions.forDaoPackageNoGetterSetter())")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        System.out.println("\n=======>>> Executing @Before advice on addAccount()");

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        System.out.println("Method: " + methodSignature);

        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            System.out.println(arg);

            if (arg instanceof Account) {
                Account account = (Account) arg;
                System.out.println("account name: " + account.getName());
                System.out.println("account name: " + account.getLevel());
            }
        }
    }
}
