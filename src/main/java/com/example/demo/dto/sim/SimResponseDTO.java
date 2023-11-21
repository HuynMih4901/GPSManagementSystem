package com.example.demo.dto.sim;

import com.example.demo.model.Provider;
import com.example.demo.model.Sim;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimResponseDTO {

  private int id;

  private String simCode;

  private String phone;

  private Double price;

  private String providerCode;

  private String providerName;

  public SimResponseDTO(Sim sim, Provider provider) {
    this.id = sim.getId();
    this.simCode = sim.getCode();
    this.phone = sim.getPhone();
    this.price = sim.getPrice();
    this.providerCode = provider.getCode();
    this.providerName = provider.getName();
  }
}
