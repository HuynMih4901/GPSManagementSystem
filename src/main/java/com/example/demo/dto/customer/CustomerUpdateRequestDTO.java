package com.example.demo.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerUpdateRequestDTO {

  @NotBlank(message = "Tên khách hàng không được để trống")
  private String name;

  @Pattern(
      regexp = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$",
      message = "Số điện thoại không đúng định dạng")
  private String phone;

  @NotBlank(message = "Địa chỉ cụ thể không được để trống")
  private String addressDetail;

  @Email(message = "Email không đúng định dạng")
  private String email;

  @NotBlank(message = "Địa chỉ không được để trống")
  private String wardCode;
}
