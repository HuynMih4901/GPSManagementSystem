package com.example.demo.dto.provider;

import com.example.demo.model.Provider;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProviderPageResponseDTO {

  private int id;

  private String code;

  private String name;

  private String contactName;

  private String email;

  public ProviderPageResponseDTO(Provider provider) {
    this.id = provider.getId();
    this.code = provider.getCode();
    this.name = provider.getName();
    this.contactName = provider.getContactName();
    this.email = provider.getEmail();
  }
}
