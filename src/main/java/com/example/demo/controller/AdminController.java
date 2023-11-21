package com.example.demo.controller;

import com.example.demo.dto.admin.AdminUpdateRequestDTO;
import com.example.demo.security.SecurityUtils;
import com.example.demo.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Admin", description = "API Admin")
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

  private final AdminService adminService;

  @PutMapping("/{adminCode}")
  @Operation(summary = "ADMIN - Cập nhật Admin")
  @ApiResponse(responseCode = "202", description = "Cập nhật admin thành công")
  public ResponseEntity<?> update(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
      @PathVariable String adminCode,
      @RequestBody @Valid AdminUpdateRequestDTO request) {
    SecurityUtils.validateToken(token, SecurityUtils.ADMINS);
    adminService.updateAdmin(request, adminCode);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }
}
