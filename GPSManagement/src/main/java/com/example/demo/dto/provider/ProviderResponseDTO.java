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

    public ProviderResponseDTO(Provider provider) {
        this.id = provider.getId();
        this.code = provider.getCode();
        this.name = provider.getName();
    }
}
