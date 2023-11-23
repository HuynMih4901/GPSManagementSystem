package com.example.demo.controller;

import com.example.demo.dto.address.DistrictResponseDTO;
import com.example.demo.dto.address.ProvinceResponseDTO;
import com.example.demo.dto.address.WardResponseDTO;
import com.example.demo.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Address", description = "Lấy thông tin tỉnh thành, quận huyện, xã phường")
@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressController {

  private final AddressService addressService;

  @GetMapping("/provinces")
  @Operation(summary = "Lấy toàn bộ tinh, thành")
  @ApiResponse(
      responseCode = "200",
      content =
          @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ProvinceResponseDTO.class)))
  public ResponseEntity<?> getProvinces() {
    return ResponseEntity.ok(addressService.getAllProvince());
  }

  @GetMapping("/districts/{provinceCode}")
  @Operation(summary = "Lấy toàn bộ quận, huyện của 1 tỉnh thành")
  @ApiResponse(
      responseCode = "200",
      content =
          @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = DistrictResponseDTO.class)))
  public ResponseEntity<?> getDistricts(@PathVariable(name = "provinceCode") String provinceCode) {
    return ResponseEntity.ok(addressService.getAllDistrictsOfProvince(provinceCode));
  }

  @GetMapping("/wards/{districtCode}")
  @Operation(summary = "Lấy toàn bộ xã, phường của 1 quận huyện")
  @ApiResponse(
      responseCode = "200",
      content =
          @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = WardResponseDTO.class)))
  public ResponseEntity<?> getWards(@PathVariable(name = "districtCode") String districtCode) {
    return ResponseEntity.ok(addressService.getAllWardsOfDistrict(districtCode));
  }
}
