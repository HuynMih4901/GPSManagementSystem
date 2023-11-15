package com.example.demo.controller;

import com.example.demo.dto.order.OrderCreateBodyDTO;
import com.example.demo.dto.order.OrderSearchParamDTO;
import com.example.demo.security.SecurityUtils;
import com.example.demo.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Order", description = "API nhập hàng")
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  @Operation(summary = "ADMIN - Nhập SIM")
  @ApiResponse(responseCode = "201", description = "Thêm SIM thành công")
  public ResponseEntity<?> create(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
      @RequestBody @Valid OrderCreateBodyDTO request) {
    SecurityUtils.validateToken(token, SecurityUtils.ADMINS);
    orderService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping
  @Operation(summary = "ADMIN - Danh sách/ Tìm đơn hàng")
  @ApiResponse(
      responseCode = "200",
      description = "Lấy thành công",
      content =
          @Content(
              array = @ArraySchema(schema = @Schema(implementation = OrderSearchParamDTO.class))))
  @PageableAsQueryParam
  public ResponseEntity<?> find(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
      OrderSearchParamDTO request,
      @Parameter(hidden = true) Pageable pageable) {
    SecurityUtils.validateToken(token, SecurityUtils.ADMINS);
    return ResponseEntity.ok(orderService.find(request, pageable));
  }
}
