package com.example.demo.dto.address;

import com.example.demo.model.Province;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProvinceResponseDTO {

  private int id;

  private String code;

  private String name;

  private String countryCode;

  public ProvinceResponseDTO(Province province) {
    this.id = province.getId();
    this.code = province.getCode();
    this.name = province.getName();
    this.countryCode = province.getCountryCode();
  }
}
