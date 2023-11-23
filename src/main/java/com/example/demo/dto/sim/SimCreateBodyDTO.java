package com.example.demo.dto.sim;

import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimCreateBodyDTO {

  @Pattern(
      regexp = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$",
      message = "Số điện thoại không đúng định dạng")
  private String phone;

  private double price;

  private String providerCode;
}
