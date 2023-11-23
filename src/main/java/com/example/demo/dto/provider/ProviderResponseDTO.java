package com.example.demo.dto.provider;

import com.example.demo.model.Provider;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProviderResponseDTO {

  private int id;

  private String code;

  private String name;

  private String contactPhone;

  private String contactName;

  private String email;

  private String addressDetail;

  private String idCard;

  private String wardCode;

  private String wardName;

  private String districtCode;

  private String districtName;

  private String provinceCode;

  private String provinceName;

  public ProviderResponseDTO(Provider provider) {
    this.id = provider.getId();
    this.code = provider.getCode();
    this.name = provider.getName();
    this.contactName = provider.getContactName();
    this.email = provider.getEmail();
  }
}
