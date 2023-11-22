package com.example.demo.dto.order;

import com.example.demo.dto.sim.SimCreateBodyDTO;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreateBodyDTO {

  @NotNull(message = "Ngày tạo không được để trống")
  private LocalDate createdDate;

  private String adminCode;

  private List<SimCreateBodyDTO> sims;
}
