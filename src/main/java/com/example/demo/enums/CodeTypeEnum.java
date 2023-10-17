package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CodeTypeEnum {

    CUS("CUSTOMER", "Customer"),
    ADMIN("ADMIN", "Admin"),
    GPS("GPS", "Service"),
    SERVICE("SERVICE", "Service");

    private final String code;
    private final String name;
}
