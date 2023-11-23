package com.example.demo.dto.provider;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProviderCreateBodyDTO {

  @NotBlank(message = "Mã nhà cung cấp không được để trống")
  private String code;

  private String name;

  @Pattern(
      regexp = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$",
      message = "Số điện thoại không đúng định dạng")
  private String contactPhone;

  private String contactName;

  @Email private String email;

  private String addressDetail;

  private String idCard;

  private String wardCode;
}
