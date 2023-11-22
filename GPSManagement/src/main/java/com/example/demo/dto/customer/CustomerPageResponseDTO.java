package com.example.demo.dto.customer;

import com.example.demo.model.Customer;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerPageResponseDTO {

  private int id;

  private String customerCode;

  private String name;

  private String phone;

  private String email;

  public CustomerPageResponseDTO(Customer customer) {
    this.id = customer.getId();
    this.customerCode = customer.getCode();
    this.name = customer.getName();
    this.phone = customer.getPhone();
    this.email = customer.getEmail();
  }
}
