package com.example.demo.dto.user;

import com.example.demo.dto.customer.CustomerCreateDTO;
import com.example.demo.enums.RoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import java.util.Set;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSignUpRequestDTO {

  @NotBlank(message = "Tài khoản không được để trống")
  @Size(max = 50)
  private String username;

  @NotBlank(message = "Mật khẩu không được để trống")
  @Size(min = 6, max = 40)
  private String password;

  @Schema(defaultValue = "[\"CUS\"]")
  private Set<RoleEnum> roles;

  @NotNull(message = "Thông tin khách hàng đăng ký không được để trống")
  private CustomerCreateDTO customer;
}
