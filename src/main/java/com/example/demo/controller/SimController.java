package com.example.demo.controller;

import com.example.demo.dto.sim.SimResponseDTO;
import com.example.demo.dto.sim.SimSearchParamDTO;
import com.example.demo.security.SecurityUtils;
import com.example.demo.service.SimService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Sim", description = "API Sim")
@RestController
@RequestMapping("/api/sim")
@RequiredArgsConstructor
public class SimController {

  private final SimService simService;

  @GetMapping("/{simCode}")
  @Operation(summary = "ADMIN - Chi tiết Sim")
  @ApiResponse(
      responseCode = "200",
      description = "Lấy thành công",
      content = @Content(schema = @Schema(implementation = SimResponseDTO.class)))
  public ResponseEntity<?> getSimBySimCode(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable String simCode) {
    SecurityUtils.validateToken(token, SecurityUtils.ADMINS);
    return ResponseEntity.ok(simService.getSimBySimCode(simCode));
  }

  @GetMapping
  @Operation(summary = "ADMIN - Danh sách/ Tìm SIM")
  @ApiResponse(
      responseCode = "200",
      description = "Lấy thành công",
      content =
          @Content(
              array = @ArraySchema(schema = @Schema(implementation = SimSearchParamDTO.class))))
  @PageableAsQueryParam
  public ResponseEntity<?> find(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
      SimSearchParamDTO request,
      @Parameter(hidden = true) Pageable pageable) {
    SecurityUtils.validateToken(token, SecurityUtils.ADMINS);
    return ResponseEntity.ok(simService.findSims(request, pageable));
  }

  @DeleteMapping("/{simId}")
  @Operation(summary = "ADMIN - Xóa SIM")
  @ApiResponse(responseCode = "204", description = "Xóa thành công")
  public ResponseEntity<?> delete(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable int simId) {
    SecurityUtils.validateToken(token, SecurityUtils.ADMINS);
    simService.delete(simId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
