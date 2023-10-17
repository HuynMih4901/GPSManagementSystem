package com.example.demo.dto.user;

import java.util.Set;

import com.example.demo.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class UserResponseDTO {

  private final String type = "Bearer";
  private String token;
  private String username;
  private Set<Role> roles;

  public UserResponseDTO(
          String accessToken, String username, Set<Role> roles) {
    this.token = accessToken;
    this.username = username;
    this.roles = roles;
  }
}
