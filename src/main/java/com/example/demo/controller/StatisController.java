package com.example.demo.controller;

import com.example.demo.dto.statis.StatisResponseDTO;
import com.example.demo.security.SecurityUtils;
import com.example.demo.service.StatisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Thống kê", description = "API thống kê")
@RestController
@RequestMapping("/api/statis")
@RequiredArgsConstructor
public class StatisController {

  private final StatisService statisService;

  @GetMapping
  @Operation(summary = "ADMIN - Thống kê")
  @ApiResponse(
      responseCode = "200",
      description = "Lấy thành công",
      content = @Content(schema = @Schema(implementation = StatisResponseDTO.class)))
  public ResponseEntity<?> getStatis(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
    SecurityUtils.validateToken(token, SecurityUtils.ADMINS);
    return ResponseEntity.ok(statisService.getStatis());
  }
}
