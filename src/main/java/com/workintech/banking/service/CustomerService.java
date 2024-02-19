package com.workintech.banking.service;

import com.workintech.banking.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Customer save(Customer customer);

    Customer find(long id);

    Customer delete(long id);
}
