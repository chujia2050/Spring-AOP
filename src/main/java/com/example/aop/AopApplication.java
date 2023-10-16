package com.example.aop;

import com.example.aop.dao.AccountDAO;
import com.example.aop.dao.MembershipDAO;
import com.example.aop.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
public class AopApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO accountDAO,
                                               MembershipDAO membershipDAO,
                                               TrafficFortuneService trafficFortuneService) {

        return runner -> {
            //demoTheBeforeAdvice(accountDAO, membershipDAO);
            //demoTheAfterReturningAdvice(accountDAO);
            //demoTheAfterThrowingAdvice(accountDAO);
            //demoTheAfterAdvice(accountDAO);
            //demoTheAroundAdvice(trafficFortuneService);
            //demoTheAroundAdviceHandleException(trafficFortuneService);
            demoTheAroundAdviceRethrowException(trafficFortuneService);
        };
    }

    private void demoTheAroundAdviceRethrowException(TrafficFortuneService trafficFortuneService) {

        System.out.println("\n\nMain Program: demoTheAroundAdviceRethrowException");
        System.out.println("Calling getFortune()");

        boolean tripWire = false;
        String data = trafficFortuneService.getFortune(tripWire);

        System.out.println("\nMy fortune is: " + data);
        System.out.println("Finished");
    }

    private void demoTheAroundAdviceHandleException(TrafficFortuneService trafficFortuneService) {
        System.out.println("\n\nMain Program: demoTheAroundAdviceHandleException");
        System.out.println("Calling getFortune()");

        boolean tripWire = false;
        String data = trafficFortuneService.getFortune(tripWire);

        System.out.println("\nMy fortune is: " + data);
        System.out.println("Finished");
    }

    private void demoTheAroundAdvice(TrafficFortuneService trafficFortuneService) {

        System.out.println("\n\nMain Program: demoTheAroundAdvice");
        System.out.println("Calling getFortune()");
        String data = trafficFortuneService.getFortune();
        System.out.println("\nMy fortune is: " + data);
        System.out.println("Finished");
    }

    private void demoTheAfterAdvice(AccountDAO accountDAO) {

        List<Account> accounts = null;
        try {
            boolean tripWire = false;
            accounts = accountDAO.findAccounts(tripWire);
        } catch (Exception e) {
            System.out.println("\n\nMain Program ... caught exception: " + e);
        }
        System.out.println("\n\nMain Program: demoTheAfterThrowingAdvice");

        System.out.println("----");
        System.out.println(accounts);
    }

    private void demoTheAfterThrowingAdvice(AccountDAO accountDAO) {
        List<Account> accounts = null;
        try {
            boolean tripWire = true;
            accounts = accountDAO.findAccounts(tripWire);
        } catch (Exception e) {
            System.out.println("\n\nMain Program ... caught exception: " + e);
        }
        System.out.println("\n\nMain Program: demoTheAfterThrowingAdvice");

        System.out.println("----");
        System.out.println(accounts);
    }

    private void demoTheAfterReturningAdvice(AccountDAO accountDAO) {

        List<Account> accounts = accountDAO.findAccounts();
        System.out.println("\n\nMain Program: demoTheAfterReturningAdvice");

        System.out.println("----");
        System.out.println(accounts);
    }

    private void demoTheBeforeAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {

        Account account = new Account();
        account.setName("Madhu");
        account.setLevel("Platinum");
        boolean vip = true;
        accountDAO.addAccount(account, vip);
        accountDAO.doWord();

        accountDAO.setName("foobar");
        accountDAO.setServiceCode("silver");
        String name = accountDAO.getName();
        accountDAO.getServiceCode();

        membershipDAO.addAccount();

//		System.out.println("\n let's call it again!\n");
//		accountDAO.addAccount(account);
//		membershipDAO.addAccount();
    }
}
