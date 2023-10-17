package com.example.demo.service.impl;

import com.example.demo.dto.customer.CustomerCreateDTO;
import com.example.demo.enums.CodeTypeEnum;
import com.example.demo.exceptions.ExceptionUtils;
import com.example.demo.exceptions.GPSException;
import com.example.demo.model.Customer;
import com.example.demo.model.Ward;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.AddressService;
import com.example.demo.service.CustomerService;
import com.example.demo.utils.GPSUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final AddressService addressService;

  @Override
  public Customer createCustomer(CustomerCreateDTO request) {
    if (customerRepository.existsByEmail(request.getEmail())) {
      throw new GPSException(ExceptionUtils.E_EMAIL_EXISTED);
    }
    if (customerRepository.existsByPhone(request.getPhone())) {
      throw new GPSException(ExceptionUtils.E_PHONE_EXISTED);
    }

    Integer maxIndex = customerRepository.findMaxIndex();
    if (maxIndex == null) {
      maxIndex = 0;
    }

    Customer customer =
        Customer.builder()
            .customerIndex(maxIndex + 1)
            .code(GPSUtils.generateCode(CodeTypeEnum.CUS.getCode(), maxIndex + 1))
            .name(request.getName())
            .addressDetail(request.getAddressDetail())
            .email(request.getEmail())
            .phone(request.getPhone())
            .ward(addressService.getWard(request.getWardCode()))
            .build();
    return customerRepository.save(customer);
  }
}
