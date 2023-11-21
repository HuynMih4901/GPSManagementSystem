package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {
  SUPER_ADMIN("SUPER_ADMIN", "Siêu admin"),
  ADMIN("ADMIN", "Cửa hàng"),
  CUS("CUS", "Khách hàng"),
  VIP_CUS("VIP_CUS", "Khách hàng VIP");

  private final String code;
  private final String name;
}
