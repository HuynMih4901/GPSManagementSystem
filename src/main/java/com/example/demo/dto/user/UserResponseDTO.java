package com.example.demo.dto.user;

import com.example.demo.model.Adminitrator;
import com.example.demo.model.Customer;
import com.example.demo.model.Role;
import java.util.Set;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDTO {

  private final String type = "Bearer";

  private String token;

  private String username;

  private Set<Role> roles;

  private String userCode;

  public UserResponseDTO(String accessToken, String username, Set<Role> roles, String userCode) {
    this.token = accessToken;
    this.username = username;
    this.roles = roles;
    this.userCode = userCode;
  }
}
