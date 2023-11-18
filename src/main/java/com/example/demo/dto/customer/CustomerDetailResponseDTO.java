package com.example.demo.dto.customer;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDetailResponseDTO {

  private int id;

  private String customerCode;

  private String name;

  private String phone;

  private String email;

  private String addressDetail;

  private String wardCode;

  private String wardName;

  private String proviceCode;

  private String provinceName;

  private String districtCode;

  private String districtName;
}
