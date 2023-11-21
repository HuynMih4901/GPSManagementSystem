package com.example.demo.dto.provider;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProviderCreateBodyDTO {

  @NotBlank(message = "Mã nhà cung cấp không được để trống")
  private String code;

  @NotBlank(message = "Tên nhà cung cấp không được để trống")
  private String name;
}
