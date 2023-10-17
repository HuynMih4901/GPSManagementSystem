package com.example.demo.controller;

import com.example.demo.dto.user.UserLoginRequestDTO;
import com.example.demo.dto.user.UserResponseDTO;
import com.example.demo.dto.user.UserSignUpRequestDTO;
import com.example.demo.security.SecurityUtils;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "Đăng ký, đăng nhập, thêm sửa xoá role")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserService userService;

  @PostMapping("/sign-up")
  @Operation(summary = "Đăng ký tài khoản cho khách hàng")
  @ApiResponse(responseCode = "201", description = "Tài khoản được đăng ký thành công")
  public ResponseEntity<?> signUp(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
      @RequestBody @Valid UserSignUpRequestDTO request) {
    SecurityUtils.validateToken(token, SecurityUtils.ADMINS);
    userService.signUp(request);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/login")
  @Operation(summary = "Đăng nhập")
  @ApiResponse(
      responseCode = "200",
      description = "Đăng nhập thành công",
      content =
          @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = UserResponseDTO.class)))
  public ResponseEntity<?> login(@RequestBody @Valid UserLoginRequestDTO request) {
    return ResponseEntity.ok(userService.login(request));
  }
}
