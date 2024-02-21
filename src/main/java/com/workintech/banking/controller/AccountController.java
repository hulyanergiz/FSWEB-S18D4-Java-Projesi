package com.workintech.banking.controller;

import com.workintech.banking.dto.AccountResponse;
import com.workintech.banking.dto.CustomerResponse;
import com.workintech.banking.entity.Account;
import com.workintech.banking.entity.Customer;
import com.workintech.banking.service.AccountService;
import com.workintech.banking.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    private final CustomerService customerService;

    @GetMapping
    public List<Account> findAll(){
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Account find(@PathVariable Long id){
        return accountService.find(id);
    }

    @PostMapping("/{customerId}")
    public AccountResponse save(@RequestBody Account account,@PathVariable long customerId){
        Customer customer=customerService.find(customerId);
        customer.getAccounts().add(account);
        account.setCustomer(customer);
        Account saved=accountService.save(account);
        return new AccountResponse(saved.getId(),saved.getAccountName(),saved.getMoneyAmount(),new CustomerResponse(customer.getId(), customer.getEmail(), customer.getSalary()));
    }

    @PutMapping("/{customerId}")
    public AccountResponse update(@RequestBody Account account,@PathVariable long customerId){
        Customer customer=customerService.find(customerId);
        Account foundAccount=null;
        for(Account a:customer.getAccounts()){
            if(account.getId()==a.getId()){
                foundAccount=a;
            }
        }
        if(foundAccount==null){
            throw new RuntimeException("Account is not found.");
        }

        int indexofFoundAccount=customer.getAccounts().indexOf(foundAccount);
        customer.getAccounts().set(indexofFoundAccount,account);
        account.setCustomer(customer);
        accountService.save(account);
        return new AccountResponse(account.getId(),account.getAccountName(),account.getMoneyAmount(),new CustomerResponse(customer.getId(), customer.getEmail(), customer.getSalary()));

    }
    @DeleteMapping("/{id}")
    public AccountResponse delete(@PathVariable long id){
        Account account=accountService.find(id);
        accountService.delete(id);
        return new AccountResponse(account.getId(),account.getAccountName(),account.getMoneyAmount(),new CustomerResponse(account.getCustomer().getId(), account.getCustomer().getEmail(), account.getCustomer().getSalary()));

    }




}
