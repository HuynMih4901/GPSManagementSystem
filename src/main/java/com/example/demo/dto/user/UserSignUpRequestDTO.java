package com.example.demo.dto.user;

import com.example.demo.enums.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

  @Email(message = "Email không đúng định dạng")
  private String email;

  @NotBlank(message = "Mật khẩu không được để trống")
  @Size(min = 6, max = 40)
  private String password;

  @Pattern(
      regexp = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$",
      message = "Số điện thoại không đúng định dạng")
  private String phone;

  private String addressDetail;

  private String wardCode;

  private Set<RoleEnum> roles;
}
