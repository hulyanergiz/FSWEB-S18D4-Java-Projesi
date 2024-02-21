package com.workintech.banking.service;

import com.workintech.banking.dto.CustomerResponse;
import com.workintech.banking.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<CustomerResponse> findAll();

    CustomerResponse save(Customer customer);

    Customer find(long id);

    Customer delete(long id);
}
