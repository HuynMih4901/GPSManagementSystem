package com.example.demo.service;


import com.example.demo.dto.customer.CustomerCreateDTO;
import com.example.demo.model.Customer;

public interface CustomerService {

    Customer createCustomer(CustomerCreateDTO request);
}
