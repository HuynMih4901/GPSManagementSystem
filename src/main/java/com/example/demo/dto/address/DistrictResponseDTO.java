package com.example.demo.dto.address;

import com.example.demo.model.District;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DistrictResponseDTO {

  private int id;

  private String code;

  private String name;

  private String provinceCode;

  public DistrictResponseDTO(District district) {
    this.id = district.getId();
    this.code = district.getCode();
    this.name = district.getName();
    this.provinceCode = district.getProvince().getCode();
  }
}
