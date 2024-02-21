package com.workintech.banking.controller;

import com.workintech.banking.dto.CustomerResponse;
import com.workintech.banking.entity.Customer;
import com.workintech.banking.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public CustomerResponse save(@RequestBody Customer customer){

        return customerService.save(customer);
    }

    @GetMapping
    public List<CustomerResponse> findAll(){

        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Customer find(@PathVariable Long id){

        return customerService.find(id);
    }


    @DeleteMapping("/{id}")
    public Customer delete(@PathVariable Long id){
        return customerService.delete(id);
    }
}
