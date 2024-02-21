package com.workintech.banking.service;

import com.workintech.banking.entity.Account;
import com.workintech.banking.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }


    @Override
    public Account save(Account account) {

        return accountRepository.save(account);
    }

    @Override
    public Account find(long id) {
        Optional<Account> accountOptional=accountRepository.findById(id);
        return accountOptional.orElseThrow(()->new RuntimeException("Account not found with id"+id));
    }

    @Override
    public Account delete(long id) {
        Account deletedAccount=find(id);
        accountRepository.delete(deletedAccount);
        return deletedAccount;
    }
}
