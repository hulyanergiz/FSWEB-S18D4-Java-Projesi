package com.workintech.banking.service;

import com.workintech.banking.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account save(Account account);

    Account find(long id);

    Account delete(long id);
}
