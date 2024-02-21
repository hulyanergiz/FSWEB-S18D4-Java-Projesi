package com.workintech.banking.service;

import com.workintech.banking.dto.CustomerResponse;
import com.workintech.banking.entity.Customer;
import com.workintech.banking.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerResponse> findAll() {
        List<CustomerResponse> responses=new ArrayList<>();
        customerRepository.findAll().forEach(customer->responses.add(new CustomerResponse(customer.getId(),customer.getEmail(),customer.getSalary())));
        return responses;
    }

    @Override
    public CustomerResponse save(Customer customer) {
        Customer saved=customerRepository.save(customer);
        return new CustomerResponse(saved.getId(),saved.getEmail(),saved.getSalary());
    }

    @Override
    public Customer find(long id) {
        Optional<Customer> customerOptional=customerRepository.findById(id);
        return customerOptional.orElseThrow(()->new RuntimeException("Customer not found with id"+id));
    }

    @Override
    public Customer delete(long id) {
        Customer deletedCustomer=find(id);
        customerRepository.delete(deletedCustomer);
        return deletedCustomer;
    }
}
