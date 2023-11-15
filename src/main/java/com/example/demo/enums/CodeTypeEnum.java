package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CodeTypeEnum {
  CUS("CUSTOMER", "Customer"),
  ADMIN("ADMIN", "Admin"),
  GPS("GPS", "Service"),
  SERVICE("SERVICE", "Service"),
  ORDER("ORDER", "Order"),
  SIM("SIM", "Sim");

  private final String code;
  private final String name;
}
