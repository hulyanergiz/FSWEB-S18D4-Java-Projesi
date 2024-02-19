package com.workintech.banking.service;

import com.workintech.banking.entity.Customer;
import com.workintech.banking.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
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
