package com.example.demo.dto.order;

import io.swagger.v3.oas.annotations.Parameter;
import java.time.LocalDate;
import lombok.*;
import org.springdoc.core.annotations.ParameterObject;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ParameterObject
public class OrderSearchParamDTO {

  @Parameter(
      name = "startDate",
      description = "Tìm kiếm theo khoảng thời gian nhập hàng",
      example = "2023-01-01")
  private LocalDate startDate;

  @Parameter(
      name = "endDate",
      description = "Tìm kiếm theo khoảng thời gian nhập hàng",
      example = "2023-11-11")
  private LocalDate endDate;
}
