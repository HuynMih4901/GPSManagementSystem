package com.example.demo.dto.customer;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.*;
import org.springdoc.core.annotations.ParameterObject;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ParameterObject
public class CustomerSearchParamDTO {

  @Parameter(
      name = "customerCode",
      description = "Tìm kiếm theo mã khách hàng",
      example = "CUSTOMER00002")
  private String customerCode;

  @Parameter(
      name = "name",
      description = "Tìm kiếm theo tên khách hàng",
      example = "Khách Hàng ProVIP")
  private String name;

  @Parameter(
      name = "phone",
      description = "Tìm kiếm theo tên số điện thoại",
      example = "0846915592")
  private String phone;

  @Parameter(
      name = "email",
      description = "Tìm kiếm theo email khách hàng",
      example = "customer2@gmail.com")
  private String email;
}
