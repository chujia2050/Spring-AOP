package com.example.aop.dao;

import com.example.aop.Account;

import java.util.List;

public interface AccountDAO {

    public List<Account> findAccounts();

    public List<Account> findAccounts(boolean tripWire);

    public void addAccount(Account account, boolean vip);

    public boolean doWord();

    public String getName();


    public void setName(String name);


    public String getServiceCode();


    public void setServiceCode(String serviceCode);


}
