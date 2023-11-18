package com.example.demo.controller;

import com.example.demo.dto.customer.CustomerDetailResponseDTO;
import com.example.demo.dto.customer.CustomerPageResponseDTO;
import com.example.demo.dto.customer.CustomerSearchParamDTO;
import com.example.demo.dto.customer.CustomerUpdateRequestDTO;
import com.example.demo.security.SecurityUtils;
import com.example.demo.service.CustomerService;
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

@Tag(name = "Customer", description = "API khách hàng")
@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @GetMapping("/{customerCode}")
  @Operation(summary = "CUSTOMER - Chi tiết khách hàng")
  @ApiResponse(
      responseCode = "200",
      description = "Lấy thành công",
      content = @Content(schema = @Schema(implementation = CustomerDetailResponseDTO.class)))
  public ResponseEntity<?> get(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable String customerCode) {
    SecurityUtils.validateToken(token, SecurityUtils.CUSTOMERS);
    return ResponseEntity.ok(customerService.getCustomer(customerCode));
  }

  @PutMapping("/{customerCode}")
  @Operation(summary = "ADMIN - Cập nhật khách hàng")
  @ApiResponse(responseCode = "202", description = "Cập nhật khách hàng thành công")
  public ResponseEntity<?> update(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
      @PathVariable String customerCode,
      @RequestBody @Valid CustomerUpdateRequestDTO request) {
    SecurityUtils.validateToken(token, SecurityUtils.ADMINS);
    customerService.updateCustomer(request, customerCode);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  //  @DeleteMapping("/{customerCode}")
  //  @Operation(summary = "ADMIN - Xoá khách hàng")
  //  @ApiResponse(responseCode = "204", description = "Xoá thành công")
  //  public ResponseEntity<?> delete(
  //      @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable String customerCode)
  // {
  //    SecurityUtils.validateToken(token, SecurityUtils.ADMINS);
  //    customerService.deleteCustomer(customerCode);
  //    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  //  }

  @GetMapping
  @Operation(summary = "ADMIN - Danh sách/ Tìm khách hàng")
  @ApiResponse(
      responseCode = "200",
      description = "Lấy thành công",
      content =
          @Content(
              array =
                  @ArraySchema(schema = @Schema(implementation = CustomerPageResponseDTO.class))))
  @PageableAsQueryParam
  public ResponseEntity<?> find(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
      CustomerSearchParamDTO request,
      @Parameter(hidden = true) Pageable pageable) {
    SecurityUtils.validateToken(token, SecurityUtils.ADMINS);
    return ResponseEntity.ok(customerService.findCustomers(request, pageable));
  }
}
