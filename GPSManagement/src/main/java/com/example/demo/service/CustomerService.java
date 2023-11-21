package com.example.demo.service;

import com.example.demo.dto.customer.*;
import com.example.demo.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

  Customer createCustomer(CustomerCreateDTO request);

  CustomerDetailResponseDTO getCustomer(String customerCode);

  Page<CustomerPageResponseDTO> findCustomers(CustomerSearchParamDTO request, Pageable pageable);

  void updateCustomer(CustomerUpdateRequestDTO request, String customerCode);

  void deleteCustomer(String customerCode);
}
