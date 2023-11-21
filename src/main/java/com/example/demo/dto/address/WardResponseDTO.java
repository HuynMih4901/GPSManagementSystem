package com.example.demo.dto.address;

import com.example.demo.model.Ward;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WardResponseDTO {

  private int id;

  private String code;

  private String name;

  private String districtCode;

  public WardResponseDTO(Ward ward) {
    this.id = ward.getId();
    this.code = ward.getCode();
    this.name = ward.getName();
    this.districtCode = ward.getDistrict().getCode();
  }
}
