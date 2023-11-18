package com.example.demo.dto.statis;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisResponseDTO {

  private Integer numberOfCustomers;

  private Integer numberOfSims;
}
