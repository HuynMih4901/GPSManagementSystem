package com.example.demo.dto.sim;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.*;
import org.springdoc.core.annotations.ParameterObject;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ParameterObject
public class SimSearchParamDTO {

  @Parameter(name = "simCode", description = "Tìm kiếm theo mã sim", example = "SIM00001")
  private String simCode;

  @Parameter(name = "phone", description = "Tìm kiếm theo số điện thoại", example = "08098266002")
  private String phone;

  @Parameter(name = "providerCode", description = "Tìm kiếm theo nhà cung cấp", example = "VINA")
  private String providerCode;
}
