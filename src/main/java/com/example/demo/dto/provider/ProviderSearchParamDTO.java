package com.example.demo.dto.provider;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.*;
import org.springdoc.core.annotations.ParameterObject;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ParameterObject
public class ProviderSearchParamDTO {

  @Parameter(name = "code", description = "Tìm theo mã nhà cung cấp", example = "BEE")
  private String code;

  @Parameter(name = "name", description = "Tìm theo tên nhà cung cấp", example = "Beeline")
  private String name;
}
