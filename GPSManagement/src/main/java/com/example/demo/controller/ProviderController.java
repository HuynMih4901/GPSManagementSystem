package com.example.demo.controller;

import com.example.demo.dto.provider.ProviderCreateBodyDTO;
import com.example.demo.dto.provider.ProviderResponseDTO;
import com.example.demo.dto.provider.ProviderSearchParamDTO;
import com.example.demo.dto.provider.ProviderUpdateBodyDTO;
import com.example.demo.security.SecurityUtils;
import com.example.demo.service.ProviderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Provider", description = "API nhà cung cấp")
@RestController
@RequestMapping("/api/provider")
@RequiredArgsConstructor
public class ProviderController {

  private final ProviderService providerService;

  @PostMapping
  @Operation(summary = "ADMIN - Thêm mới nhà cung cấp")
  @ApiResponse(responseCode = "201", description = "Thêm nhà cung cấp thành công")
  public ResponseEntity<?> create(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
      @RequestBody @Valid ProviderCreateBodyDTO request) {
    SecurityUtils.validateToken(token, SecurityUtils.ADMINS);
    providerService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping("/{id}")
  @Operation(summary = "ADMIN - Cập nhật nhà cung cấp")
  @ApiResponse(responseCode = "202", description = "Cập nhật cung cấp thành công")
  public ResponseEntity<?> update(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
      @PathVariable int id,
      @RequestBody @Valid ProviderUpdateBodyDTO request) {
    SecurityUtils.validateToken(token, SecurityUtils.ADMINS);
    providerService.update(id, request);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @GetMapping("/{id}")
  @Operation(summary = "ADMIN - Chi tiết nhà cung cấp")
  @ApiResponse(
      responseCode = "200",
      description = "Lấy thành công",
      content = @Content(schema = @Schema(implementation = ProviderResponseDTO.class)))
  public ResponseEntity<?> get(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable int id) {
    SecurityUtils.validateToken(token, SecurityUtils.ADMINS);
    return ResponseEntity.ok(providerService.get(id));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "ADMIN - Xoá nhà cung cấp")
  @ApiResponse(responseCode = "204", description = "Xoá thành công")
  public ResponseEntity<?> delete(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable int id) {
    SecurityUtils.validateToken(token, SecurityUtils.ADMINS);
    providerService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @GetMapping
  @Operation(summary = "ADMIN - Danh sách/ Tìm nhà cung cấp")
  @ApiResponse(
      responseCode = "200",
      description = "Lấy thành công",
      content =
          @Content(
              array = @ArraySchema(schema = @Schema(implementation = ProviderResponseDTO.class))))
  public ResponseEntity<?> find(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
      ProviderSearchParamDTO request) {
    SecurityUtils.validateToken(token, SecurityUtils.ADMINS);
    return ResponseEntity.ok(providerService.find(request));
  }
}
